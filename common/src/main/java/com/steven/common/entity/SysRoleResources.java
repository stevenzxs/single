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
@TableName("sys_role_resources")
public class SysRoleResources implements Serializable {

    private static final long serialVersionUID = 1L;

    private String rRId;

    private String sysResourceId;

    private String sysRoleId;

    public String getrRId() {
        return rRId;
    }

    public void setrRId(String rRId) {
        this.rRId = rRId;
    }
    public String getSysResourceId() {
        return sysResourceId;
    }

    public void setSysResourceId(String sysResourceId) {
        this.sysResourceId = sysResourceId;
    }
    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    @Override
    public String toString() {
        return "SysRoleResources{" +
            "rRId=" + rRId +
            ", sysResourceId=" + sysResourceId +
            ", sysRoleId=" + sysRoleId +
        "}";
    }
}
