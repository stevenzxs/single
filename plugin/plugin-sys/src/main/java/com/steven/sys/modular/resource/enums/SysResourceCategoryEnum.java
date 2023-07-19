package com.steven.sys.modular.resource.enums;

import lombok.Getter;
import com.steven.common.exception.CommonException;

/**
 * 资源分类枚举
 *
 **/
@Getter
public enum SysResourceCategoryEnum {

    /** 模块 */
    MODULE("MODULE"),

    /** 单页面 */
    SPA("SPA"),

    /** 菜单 */
    MENU("MENU"),

    /** 按钮 */
    BUTTON("BUTTON");

    private final String value;

    SysResourceCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = MODULE.getValue().equals(value) || SPA.getValue().equals(value) || MENU.getValue().equals(value)
                || BUTTON.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的资源分类：{}", value);
        }
    }
}
