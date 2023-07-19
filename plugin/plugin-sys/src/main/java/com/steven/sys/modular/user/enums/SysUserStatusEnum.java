package com.steven.sys.modular.user.enums;

import lombok.Getter;
import com.steven.common.exception.CommonException;

/**
 * 用户状态枚举
 */
@Getter
public enum SysUserStatusEnum {

    /**
     * 正常
     */
    ENABLE("ENABLE"),

    /**
     * 停用
     */
    DISABLED("DISABLED");

    private final String value;

    SysUserStatusEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = ENABLE.getValue().equals(value) || DISABLED.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的用户状态：{}", value);
        }
    }
}
