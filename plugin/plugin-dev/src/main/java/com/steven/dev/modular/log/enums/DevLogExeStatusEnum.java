package com.steven.dev.modular.log.enums;

import lombok.Getter;

/**
 * 日志执行状态枚举
 **/
@Getter
public enum DevLogExeStatusEnum {

    /** 成功 */
    SUCCESS("SUCCESS"),

    /** 失败 */
    FAIL("FAIL");

    private final String value;

    DevLogExeStatusEnum(String value) {
        this.value = value;
    }
}
