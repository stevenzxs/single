package com.steven.dev.modular.log.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.dev.modular.log.entity.DevLog;
import com.steven.dev.modular.log.param.DevLogDeleteParam;
import com.steven.dev.modular.log.param.DevLogPageParam;
import com.steven.dev.modular.log.result.DevLogOpBarChartDataResult;
import com.steven.dev.modular.log.result.DevLogOpPieChartDataResult;
import com.steven.dev.modular.log.result.DevLogVisLineChartDataResult;
import com.steven.dev.modular.log.result.DevLogVisPieChartDataResult;

import java.util.List;

/**
 * 日志Service接口
 */
public interface DevLogService extends IService<DevLog> {

    /**
     * 获取日志分页
     */
    Page<DevLog> page(DevLogPageParam devLogPageParam);

    /**
     * 清空日志
     */
    void delete(DevLogDeleteParam devLogDeleteParam);

    /**
     * 获取访问日志折线图数据
     */
    List<DevLogVisLineChartDataResult> visLogLineChartData();

    /**
     * 获取访问日志饼状图数据
     */
    List<DevLogVisPieChartDataResult> visLogPieChartData();

    /**
     * 获取操作日志柱状图数据
     */
    List<DevLogOpBarChartDataResult> opLogBarChartData();

    /**
     * 获取操作日志饼状图数据
     */
    List<DevLogOpPieChartDataResult> opLogPieChartData();
}
