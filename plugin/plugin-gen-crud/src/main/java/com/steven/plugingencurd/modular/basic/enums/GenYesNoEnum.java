package com.steven.plugingencurd.modular.basic.enums;

import lombok.Getter;

/**
 * 是与否枚举
 *
 **/
@Getter
public enum GenYesNoEnum {

    /** 是 */
    Y("Y"),

    /** 否 */
    N("N");

    private final String value;

    GenYesNoEnum(String value) {
        this.value = value;
    }
}
