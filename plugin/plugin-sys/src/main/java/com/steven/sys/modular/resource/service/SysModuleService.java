package com.steven.sys.modular.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.sys.modular.resource.entity.SysModule;
import com.steven.sys.modular.resource.param.module.SysModuleAddParam;
import com.steven.sys.modular.resource.param.module.SysModuleEditParam;
import com.steven.sys.modular.resource.param.module.SysModuleIdParam;
import com.steven.sys.modular.resource.param.module.SysModulePageParam;

import java.util.List;

/**
 * 模块Service接口
 *
 **/
public interface SysModuleService extends IService<SysModule> {

    /**
     * 获取模块分页
     *
     */
    Page<SysModule> page(SysModulePageParam sysModulePageParam);

    /**
     * 添加模块
     *
     */
    void add(SysModuleAddParam sysModuleAddParam);

    /**
     * 编辑模块
     */
    void edit(SysModuleEditParam sysModuleEditParam);

    /**
     * 删除模块
     */
    void delete(List<SysModuleIdParam> sysModuleIdParamList);

    /**
     * 获取模块详情
     */
    SysModule detail(SysModuleIdParam sysModuleIdParam);

    /**
     * 获取模块详情
     */
    SysModule queryEntity(String id);
}
