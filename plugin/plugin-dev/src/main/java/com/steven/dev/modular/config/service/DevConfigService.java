package com.steven.dev.modular.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.dev.modular.config.entity.DevConfig;
import com.steven.dev.modular.config.param.*;

import java.util.List;

/**
 * 配置Service接口
 **/
public interface DevConfigService extends IService<DevConfig> {

    /**
     * 根据键获取值
     **/
    String getValueByKey(String key);

    /**
     * 获取配置分页
     */
    Page<DevConfig> page(DevConfigPageParam devConfigPageParam);

    /**
     * 获取配置列表
     */
    List<DevConfig> list(DevConfigListParam devConfigListParam);

    /**
     * 添加配置
     */
    void add(DevConfigAddParam devConfigAddParam);

    /**
     * 编辑配置
     */
    void edit(DevConfigEditParam devConfigEditParam);

    /**
     * 删除配置
     */
    void delete(List<DevConfigIdParam> devConfigIdParamList);

    /**
     * 获取配置详情
     */
    DevConfig detail(DevConfigIdParam devConfigIdParam);

    /**
     * 获取配置详情
     */
    DevConfig queryEntity(String id);

    /**
     * 配置批量更新
     **/
    void editBatch(List<DevConfigBatchParam> devConfigBatchParamList);
}
