package com.steven.sys.modular.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.sys.modular.resource.entity.SysButton;
import com.steven.sys.modular.resource.param.button.SysButtonAddParam;
import com.steven.sys.modular.resource.param.button.SysButtonEditParam;
import com.steven.sys.modular.resource.param.button.SysButtonIdParam;
import com.steven.sys.modular.resource.param.button.SysButtonPageParam;

import java.util.List;

/**
 * 按钮Service接口
 *
 **/
public interface SysButtonService extends IService<SysButton> {

    /**
     * 获取按钮分页
     *
     */
    Page<SysButton> page(SysButtonPageParam sysButtonPageParam);

    /**
     * 添加按钮
     *
     */
    void add(SysButtonAddParam sysButtonAddParam);

    /**
     * 代码生成按钮插入
     *
     **/
    void addForGenButton(String menuId, String className, String functionName);

    /**
     * 编辑按钮
     */
    void edit(SysButtonEditParam sysButtonEditParam);

    /**
     * 删除按钮
     */
    void delete(List<SysButtonIdParam> sysButtonIdParamList);

    /**
     * 获取按钮详情
     */
    SysButton detail(SysButtonIdParam sysButtonIdParam);

    /**
     * 获取按钮详情
     */
    SysButton queryEntity(String id);
}
