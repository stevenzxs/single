package com.steven.sys.api;

import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 职位API
 **/
public interface SysPositionApi {

    /**
     * 根据id获取名称
     **/
    String getNameById(String positionId);

    /**
     * 获取职位选择器
     **/
    List<JSONObject> positionSelector(String orgId, String searchKey);
}
