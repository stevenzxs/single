package com.steven.sys.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.steven.common.enums.CommonSortOrderEnum;
import com.steven.common.exception.CommonException;
import com.steven.common.listener.CommonDataChangeEventCenter;
import com.steven.common.page.CommonPageRequest;
import com.steven.sys.core.enums.SysBuildInEnum;
import com.steven.sys.core.enums.SysDataTypeEnum;
import com.steven.sys.modular.relation.entity.SysRelation;
import com.steven.sys.modular.relation.enums.SysRelationCategoryEnum;
import com.steven.sys.modular.relation.service.SysRelationService;
import com.steven.sys.modular.resource.entity.SysMenu;
import com.steven.sys.modular.resource.entity.SysModule;
import com.steven.sys.modular.resource.enums.SysResourceCategoryEnum;
import com.steven.sys.modular.resource.mapper.SysModuleMapper;
import com.steven.sys.modular.resource.param.module.SysModuleAddParam;
import com.steven.sys.modular.resource.param.module.SysModuleEditParam;
import com.steven.sys.modular.resource.param.module.SysModuleIdParam;
import com.steven.sys.modular.resource.param.module.SysModulePageParam;
import com.steven.sys.modular.resource.service.SysMenuService;
import com.steven.sys.modular.resource.service.SysModuleService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模块Service接口实现类
 *
 **/
@Service
public class SysModuleServiceImpl extends ServiceImpl<SysModuleMapper, SysModule> implements SysModuleService {

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysRelationService sysRelationService;

    @Override
    public Page<SysModule> page(SysModulePageParam sysModulePageParam) {
        QueryWrapper<SysModule> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysModule::getCategory, SysResourceCategoryEnum.MODULE.getValue());
        if(ObjectUtil.isNotEmpty(sysModulePageParam.getSearchKey())) {
            queryWrapper.lambda().like(SysModule::getTitle, sysModulePageParam.getSearchKey());
        }
        if(ObjectUtil.isAllNotEmpty(sysModulePageParam.getSortField(), sysModulePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sysModulePageParam.getSortOrder());
            queryWrapper.orderBy(true, sysModulePageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sysModulePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SysModule::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SysModuleAddParam sysModuleAddParam) {
        SysModule sysModule = BeanUtil.toBean(sysModuleAddParam, SysModule.class);
        boolean repeatTitle = this.count(new LambdaQueryWrapper<SysModule>().eq(SysModule::getCategory,
                SysResourceCategoryEnum.MODULE.getValue()).eq(SysModule::getTitle, sysModule.getTitle())) > 0;
        if(repeatTitle) {
            throw new CommonException("存在重复的模块，名称为：{}", sysModule.getTitle());
        }
        sysModule.setCode(RandomUtil.randomString(10));
        sysModule.setCategory(SysResourceCategoryEnum.MODULE.getValue());
        this.save(sysModule);

        // 发布增加事件
        CommonDataChangeEventCenter.doAddWithData(SysDataTypeEnum.RESOURCE.getValue(), JSONUtil.createArray().put(sysModule));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SysModuleEditParam sysModuleEditParam) {
        SysModule sysModule = this.queryEntity(sysModuleEditParam.getId());
        BeanUtil.copyProperties(sysModuleEditParam, sysModule);
        boolean repeatTitle = this.count(new LambdaQueryWrapper<SysModule>().eq(SysModule::getCategory,
                SysResourceCategoryEnum.MODULE.getValue()).eq(SysModule::getTitle, sysModule.getTitle())
                .ne(SysModule::getId, sysModule.getId())) > 0;
        if(repeatTitle) {
            throw new CommonException("存在重复的模块，名称为：{}", sysModule.getTitle());
        }
        this.updateById(sysModule);

        // 发布更新事件
        CommonDataChangeEventCenter.doUpdateWithData(SysDataTypeEnum.RESOURCE.getValue(), JSONUtil.createArray().put(sysModule));
    }

    @Override
    public void delete(List<SysModuleIdParam> sysModuleIdParamList) {
        List<String> sysModuleIdList = CollStreamUtil.toList(sysModuleIdParamList, SysModuleIdParam::getId);
        if(ObjectUtil.isNotEmpty(sysModuleIdList)) {
            boolean containsSystemModule = this.listByIds(sysModuleIdList).stream().map(SysModule::getCode)
                    .collect(Collectors.toSet()).contains(SysBuildInEnum.BUILD_IN_MODULE_CODE.getValue());
            if(containsSystemModule) {
                throw new CommonException("不可删除系统内置模块");
            }

            // 获取模块下的菜单、按钮
            List<SysMenu> allMenuList = sysMenuService.list(new LambdaQueryWrapper<SysMenu>()
                    .in(SysMenu::getCategory, CollectionUtil.newArrayList(SysResourceCategoryEnum.MENU.getValue(),
                            SysResourceCategoryEnum.BUTTON.getValue())));
            if(ObjectUtil.isNotEmpty(allMenuList)) {
                List<String> toDeleteMenuIdList = CollectionUtil.newArrayList(sysModuleIdList);
                allMenuList.stream().filter(sysMenu -> sysModuleIdList.contains(sysMenu.getModule()))
                        .collect(Collectors.toList()).forEach(sysMenu -> toDeleteMenuIdList
                        .addAll(sysMenuService.getChildListById(allMenuList, sysMenu.getId(), true).stream()
                                .map(SysMenu::getId).collect(Collectors.toList())));
                if(ObjectUtil.isNotEmpty(toDeleteMenuIdList)) {
                    // 清除对应的角色与资源信息
                    sysRelationService.remove(new LambdaUpdateWrapper<SysRelation>().in(SysRelation::getTargetId, toDeleteMenuIdList)
                            .eq(SysRelation::getCategory, SysRelationCategoryEnum.SYS_ROLE_HAS_RESOURCE.getValue()));
                    // 执行删除
                    this.removeByIds(toDeleteMenuIdList);

                    // 发布删除事件
                    CommonDataChangeEventCenter.doDeleteWithDataId(SysDataTypeEnum.RESOURCE.getValue(), toDeleteMenuIdList);
                }
            }
        }
    }

    @Override
    public SysModule detail(SysModuleIdParam sysModuleIdParam) {
        return this.queryEntity(sysModuleIdParam.getId());
    }

    @Override
    public SysModule queryEntity(String id) {
        SysModule sysModule = this.getById(id);
        if(ObjectUtil.isEmpty(sysModule)) {
            throw new CommonException("模块不存在，id值为：{}", id);
        }
        return sysModule;
    }
}
