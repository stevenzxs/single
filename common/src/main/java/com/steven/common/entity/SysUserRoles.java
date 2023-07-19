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
@TableName("sys_user_roles")
public class SysUserRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uRId;

    private String sysRoleId;

    private String sysUserId;

    public String getuRId() {
        return uRId;
    }

    public void setuRId(String uRId) {
        this.uRId = uRId;
    }
    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    @Override
    public String toString() {
        return "SysUserRoles{" +
            "uRId=" + uRId +
            ", sysRoleId=" + sysRoleId +
            ", sysUserId=" + sysUserId +
        "}";
    }
}
