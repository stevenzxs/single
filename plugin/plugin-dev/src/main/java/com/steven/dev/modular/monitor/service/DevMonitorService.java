package com.steven.dev.modular.monitor.service;

import com.steven.dev.modular.monitor.result.DevMonitorServerResult;

/**
 * 监控Service接口
 */
public interface DevMonitorService {

    /**
     * 获取服务器监控信息
     */
    DevMonitorServerResult serverInfo();
}
