package com.steven.mobile.modular.resource.param.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 移动端菜单tree查询参数
 **/
@Getter
@Setter
public class MobileMenuTreeParam {

    /** 关键词 */
    @ApiModelProperty(value = "关键词")
    private String searchKey;

    /** 模块 */
    @ApiModelProperty(value = "模块")
    private String module;

}
