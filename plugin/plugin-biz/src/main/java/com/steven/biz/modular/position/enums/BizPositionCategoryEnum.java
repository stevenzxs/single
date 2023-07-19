package com.steven.biz.modular.position.enums;

import lombok.Getter;
import com.steven.common.exception.CommonException;

/**
 * 岗位分类枚举
 **/
@Getter
public enum BizPositionCategoryEnum {

    /** 高层 */
    HIGH("HIGH"),

    /** 中层 */
    MIDDLE("MIDDLE"),

    /** 基层 */
    LOW("LOW");

    private final String value;

    BizPositionCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = HIGH.getValue().equals(value) || MIDDLE.getValue().equals(value) || LOW.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的岗位分类：{}", value);
        }
    }
}
