package com.steven.sys.api;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 组织API
 **/
public interface SysOrgApi {

    /**
     * 根据id获取名称
     **/
    String getNameById(String orgId);

    /**
     * 根据组织id获取部门主管id
     **/
    String getSupervisorIdByOrgId(String orgId);

    /**
     * 获取组织树选择器
     **/
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取组织列表选择器
     **/
    List<JSONObject> orgListSelector(String parentId);
}
