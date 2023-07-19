package com.steven.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stevenzxs
 * @since 2023-06-11
 */
@TableName("sys_resource")
public class SysResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String orderNum;

    private String rType;

    private String url;

    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SysResource{" +
            "id=" + id +
            ", name=" + name +
            ", orderNum=" + orderNum +
            ", rType=" + rType +
            ", url=" + url +
            ", parentId=" + parentId +
        "}";
    }
}
