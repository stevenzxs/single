package com.steven.sys.api;

/**
 * 菜单API
 **/
public interface SysMenuApi {
    /**
     * 代码生成菜单插入
     **/
    String addForGenMenu(String parentId, String busName, String module, String title, String path);
}
