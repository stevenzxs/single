package com.steven.sys.modular.resource.param.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 模块选择器参数
 *
 **/
@Getter
@Setter
public class SysMenuSelectorModuleParam {

    /** 名称关键词 */
    @ApiModelProperty(value = "名称关键词")
    private String searchKey;
}
