package com.steven.plugingencurd.modular.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.plugingencurd.modular.basic.entity.GenBasic;
import com.steven.plugingencurd.modular.basic.param.*;
import com.steven.plugingencurd.modular.basic.result.GenBasicPreviewResult;
import com.steven.plugingencurd.modular.basic.result.GenBasicTableColumnResult;
import com.steven.plugingencurd.modular.basic.result.GenBasicTableResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 代码生成基础Service接口
 *
 **/
public interface GenBasicService extends IService<GenBasic> {

    /**
     * 查询代码生成基础分页
     *
     */
    Page<GenBasic> page(GenBasicPageParam genBasicPageParam);

    /**
     * 添加代码生成基础
     *
     */
    GenBasic add(GenBasicAddParam genBasicAddParam);

    /**
     * 编辑代码生成基础
     *
     */
    GenBasic edit(GenBasicEditParam genBasicEditParam);

    /**
     * 删除代码生成基础
     *
     */
    void delete(List<GenBasicIdParam> genBasicIdParamList);

    /**
     * 获取代码生成基础详情
     *
     */
    GenBasic detail(GenBasicIdParam genBasicIdParam);

    /**
     * 获取代码生成基础详情
     *
     **/
    GenBasic queryEntity(String id);

    /**
     * 获取所有表信息
     *
     **/
    List<GenBasicTableResult> tables();

    /**
     * 获取表内所有字段信息
     *
     **/
    List<GenBasicTableColumnResult> tableColumns(GenBasicTableColumnParam genBasicTableColumnParam);

    /**
     * 执行代码生成
     *
     **/
    void execGenZip(GenBasicIdParam genBasicIdParam, HttpServletResponse response) throws IOException;

    /**
     * 执行代码生成
     *
     **/
    void execGenPro(GenBasicIdParam genBasicIdParam, HttpServletResponse response) throws IOException;

    /**
     * 预览代码生成
     *
     **/
    GenBasicPreviewResult previewGen(GenBasicIdParam genBasicIdParam);
}
