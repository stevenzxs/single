package com.steven.sys.modular.role.provider;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.steven.sys.api.SysRoleApi;
import com.steven.sys.core.enums.SysBuildInEnum;
import com.steven.sys.modular.relation.entity.SysRelation;
import com.steven.sys.modular.relation.enums.SysRelationCategoryEnum;
import com.steven.sys.modular.relation.service.SysRelationService;
import com.steven.sys.modular.resource.entity.SysButton;
import com.steven.sys.modular.resource.entity.SysMenu;
import com.steven.sys.modular.resource.enums.SysResourceCategoryEnum;
import com.steven.sys.modular.resource.service.SysButtonService;
import com.steven.sys.modular.resource.service.SysMenuService;
import com.steven.sys.modular.role.entity.SysRole;
import com.steven.sys.modular.role.enums.SysRoleCategoryEnum;
import com.steven.sys.modular.role.param.SysRoleGrantResourceParam;
import com.steven.sys.modular.role.param.SysRoleSelectorRoleParam;
import com.steven.sys.modular.role.service.SysRoleService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色API接口提供者
 **/
@Service
public class SysRoleApiProvider implements SysRoleApi {

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysButtonService sysButtonService;

    @Resource
    private SysRelationService sysRelationService;

    @Override
    public boolean orgHasRole(List<String> orgIdList) {
        return sysRoleService.count(new LambdaQueryWrapper<SysRole>().in(SysRole::getOrgId, orgIdList)) > 0;
    }

    @Override
    public List<JSONObject> roleSelector(String orgId, String category, String searchKey) {
        SysRoleSelectorRoleParam sysRoleSelectorRoleParam = new SysRoleSelectorRoleParam();
        sysRoleSelectorRoleParam.setOrgId(orgId);
        sysRoleSelectorRoleParam.setCategory(category);
        sysRoleSelectorRoleParam.setSearchKey(searchKey);
        return sysRoleService.roleSelector(sysRoleSelectorRoleParam).stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void grantForGenMenuAndButton(String menuId) {
        String superAdminRoleId = sysRoleService.getOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getCode, SysBuildInEnum.BUILD_IN_ROLE_CODE.getValue())
                .eq(SysRole::getCategory, SysRoleCategoryEnum.GLOBAL.getValue())).getId();
        SysRoleGrantResourceParam sysRoleGrantResourceParam = new SysRoleGrantResourceParam();
        sysRoleGrantResourceParam.setId(superAdminRoleId);
        SysMenu sysMenu = sysMenuService.queryEntity(menuId);
        SysRoleGrantResourceParam.SysRoleGrantResource sysRoleGrantResource = new SysRoleGrantResourceParam.SysRoleGrantResource();
        sysRoleGrantResource.setMenuId(sysMenu.getId());
        List<String> buttonIdList = sysButtonService.list(new LambdaQueryWrapper<SysButton>().eq(SysButton::getParentId,
                sysMenu.getId()).eq(SysButton::getCategory, SysResourceCategoryEnum.BUTTON.getValue())).stream()
                .map(SysButton::getId).collect(Collectors.toList());
        sysRoleGrantResource.setButtonInfo(buttonIdList);
        sysRoleGrantResourceParam.setGrantInfoList(CollectionUtil.newArrayList(sysRoleGrantResource));

        List<String> menuIdList = sysRoleGrantResourceParam.getGrantInfoList().stream()
                .map(SysRoleGrantResourceParam.SysRoleGrantResource::getMenuId).collect(Collectors.toList());
        List<String> extJsonList = sysRoleGrantResourceParam.getGrantInfoList().stream()
                .map(JSONUtil::toJsonStr).collect(Collectors.toList());

        List<String> existMenuIdList = sysMenuService.list(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getCategory,
                SysResourceCategoryEnum.MENU.getValue())).stream().map(SysMenu::getId).collect(Collectors.toList());
        if(ObjectUtil.isNotEmpty(existMenuIdList)) {
            sysRelationService.remove(new LambdaQueryWrapper<SysRelation>().eq(SysRelation::getObjectId, superAdminRoleId)
                    .eq(SysRelation::getCategory, SysRelationCategoryEnum.SYS_ROLE_HAS_RESOURCE.getValue()).notIn(SysRelation::getTargetId, existMenuIdList));
        }
        sysRelationService.saveRelationBatchWithAppend(superAdminRoleId, menuIdList, SysRelationCategoryEnum.SYS_ROLE_HAS_RESOURCE.getValue(), extJsonList);
    }
}
