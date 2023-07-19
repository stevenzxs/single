package com.steven.common.listener;

import cn.hutool.json.JSONArray;

import java.util.List;

/**
 * 通用数据变化侦听器，你可以实现该侦听器接口，在数据新增、更新、删除时进行一些AOP操作
 *
 **/
public interface CommonDataChangeListener {

    /**
     * 执行添加，ID集合
     *
     **/
    void doAddWithDataIdList(String dataType, List<String> dataIdList);

    /**
     * 执行添加，数据集合
     *
     **/
    void doAddWithDataList(String dataType, JSONArray jsonArray);

    /**
     * 执行更新，ID集合
     *
     **/
    void doUpdateWithDataIdList(String dataType, List<String> dataIdList);

    /**
     * 执行更新，数据集合
     *
     **/
    void doUpdateWithDataList(String dataType, JSONArray jsonArray);

    /**
     * 执行删除，ID集合
     *
     **/
    void doDeleteWithDataIdList(String dataType, List<String> dataIdList);
}
