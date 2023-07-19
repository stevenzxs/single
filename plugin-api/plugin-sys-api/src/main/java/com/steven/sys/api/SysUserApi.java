package com.steven.sys.api;

import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 用户Api
 **/
public interface SysUserApi {

    /**
     * 根据用户id获取用户对象，没有则返回null
     **/
    JSONObject getUserByIdWithoutException(String userId);

    /**
     * 根据用户id获取用户对象列表，没有的则为空，都没有则返回空集合
     **/
    List<JSONObject> getUserListByIdListWithoutException(List<String> userIdList);

    /**
     * 根据用户id获取用户对象，没有则抛出异常
     **/
    JSONObject getUserByIdWithException(String userId);

    /**
     * 根据用户id获取用户对象列表，只要有一个没有则抛出异常
     **/
    List<JSONObject> getUserListByIdWithException(List<String> userIdList);

    /**
     * 获取用户拥有角色
     */
    List<String> ownRole(String userId);

    /**
     * 给用户授权角色
     */
    void grantRole(String userId, List<String> roleIdList);

    /**
     * 根据组织id集合获取组织下用户id集合
     **/
    List<String> getUserIdListByOrgIdList(List<String> orgIdList);

    /**
     * 根据职位id集合获取职位下用户id集合
     **/
    List<String> getUserIdListByPositionIdList(List<String> positionIdList);

    /**
     * 根据用户id和组织id和职位id获取上级主管id
     **/
    String getSupervisorIdByUserIdAndOrgIdAndPositionId(String userId, String orgId, String positionId);

    /**
     * 获取用户选择器
     */
    List<JSONObject> userSelector(String orgId, String searchKey);
}
