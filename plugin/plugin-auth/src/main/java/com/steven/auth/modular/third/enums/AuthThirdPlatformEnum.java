package com.steven.auth.modular.third.enums;

import lombok.Getter;
import com.steven.common.exception.CommonException;

/**
 * 第三方登录平台枚举
 **/
@Getter
public enum AuthThirdPlatformEnum {

    /**
     * GITEE
     */
    GITEE("GITEE"),

    /**
     * WECHAT
     */
    WECHAT("WECHAT");

    private final String value;

    AuthThirdPlatformEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = GITEE.getValue().equals(value) || WECHAT.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的第三方平台：{}", value);
        }
    }
}
