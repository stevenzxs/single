package com.steven.mobile.api;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 移动端菜单API
 **/
public interface MobileMenuApi {

    /**
     * 获取移动端菜单授权树
     **/
    List<JSONObject> mobileMenuTreeSelector();

    /**
     * 获取移动端登录菜单树
     **/
    List<Tree<String>> loginMobileMenuTree(List<String> menuIdList);
}
