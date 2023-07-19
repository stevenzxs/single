package com.steven.dev.modular.dict.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.dev.modular.dict.entity.DevDict;
import com.steven.dev.modular.dict.param.*;

import java.util.List;

/**
 * 字典Service接口
 **/
public interface DevDictService extends IService<DevDict> {

    /**
     * 获取字典分页
     */
    Page<DevDict> page(DevDictPageParam devDictPageParam);

    /**
     * 获取字典列表
     */
    List<DevDict> list(DevDictListParam devDictListParam);

    /**
     * 获取字典树
     */
    List<Tree<String>> tree(DevDictTreeParam devDictTreeParam);

    /**
     * 添加字典
     */
    void add(DevDictAddParam devDictAddParam);

    /**
     * 编辑字典
     */
    void edit(DevDictEditParam devDictEditParam);

    /**
     * 删除字典
     */
    void delete(List<DevDictIdParam> devDictIdParamList);

    /**
     * 获取字典详情
     */
    DevDict detail(DevDictIdParam devDictIdParam);

    /**
     * 获取字典详情
     */
    DevDict queryEntity(String id);
}
