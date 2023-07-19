package com.steven.biz.modular.user.enums;

import lombok.Getter;
import com.steven.common.exception.CommonException;

/**
 * 人员状态枚举
 */
@Getter
public enum BizUserStatusEnum {

    /**
     * 正常
     */
    ENABLE("ENABLE"),

    /**
     * 停用
     */
    DISABLED("DISABLED");

    private final String value;

    BizUserStatusEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = ENABLE.getValue().equals(value) || DISABLED.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的人员状态：{}", value);
        }
    }
}
