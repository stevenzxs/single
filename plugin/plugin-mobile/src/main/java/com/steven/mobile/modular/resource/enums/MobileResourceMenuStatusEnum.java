package com.steven.mobile.modular.resource.enums;

import lombok.Getter;
import com.steven.common.exception.CommonException;

/**
 * 移动端资菜单状态枚举
 **/
@Getter
public enum MobileResourceMenuStatusEnum {

    /** 可用 */
    ENABLE("ENABLE"),

    /** 不可用 */
    DISABLED("DISABLED");

    private final String value;

    MobileResourceMenuStatusEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = ENABLE.getValue().equals(value) || DISABLED.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的资源分类：{}", value);
        }
    }
}
