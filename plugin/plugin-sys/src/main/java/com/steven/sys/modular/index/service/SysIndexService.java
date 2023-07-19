package com.steven.sys.modular.index.service;

import com.steven.common.pojo.CommonValidList;
import com.steven.sys.modular.index.param.*;
import com.steven.sys.modular.index.result.*;

import java.util.List;

/**
 * 系统首页Service接口
 *
 */
public interface SysIndexService {

    /**
     * 添加当前用户日程
     *
     */
    void addSchedule(SysIndexScheduleAddParam sysIndexScheduleAddParam);

    /**
     * 删除日程
     *
     */
    void deleteSchedule(CommonValidList<SysIndexScheduleIdParam> sysIndexScheduleIdParamList);

    /**
     * 获取当前用户日程列表
     *
     */
    List<SysIndexScheduleListResult> scheduleList(SysIndexScheduleListParam sysIndexScheduleListParam);

    /**
     * 获取当前用户站内信列表
     *
     */
    List<SysIndexMessageListResult> messageList(SysIndexMessageListParam sysIndexMessageListParam);

    /**
     * 获取站内信详情
     *
     */
    SysIndexMessageDetailResult messageDetail(SysIndexMessageIdParam sysIndexMessageIdParam);

    /**
     * 获取当前用户访问日志列表
     *
     */
    List<SysIndexVisLogListResult> visLogList();

    /**
     * 获取当前用户操作日志列表
     *
     */
    List<SysIndexOpLogListResult> opLogList();
}
