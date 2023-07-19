package com.steven.plugingencurd.modular.basic.enums;

import lombok.Getter;

/**
 * 生成方式枚举
 *
 **/
@Getter
public enum GenTypeEnum {

    /** 压缩包 */
    ZIP("ZIP"),

    /** 项目内 */
    PRO("PRO");

    private final String value;

    GenTypeEnum(String value) {
        this.value = value;
    }
}
