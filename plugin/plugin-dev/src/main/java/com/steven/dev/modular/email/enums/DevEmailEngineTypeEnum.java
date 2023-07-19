package com.steven.dev.modular.email.enums;

import lombok.Getter;

/**
 * 邮件发送引擎类型枚举
 **/
@Getter
public enum DevEmailEngineTypeEnum {

    /** 本地 */
    LOCAL("LOCAL"),

    /** 阿里云 */
    ALIYUN("ALIYUN"),

    /** 腾讯云 */
    TENCENT("TENCENT");

    private final String value;

    DevEmailEngineTypeEnum(String value) {
        this.value = value;
    }
}
