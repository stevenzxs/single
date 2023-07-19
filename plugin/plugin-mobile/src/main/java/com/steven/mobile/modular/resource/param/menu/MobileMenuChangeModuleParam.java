
package com.steven.mobile.modular.resource.param.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 移动端菜单更改所属模块参数
 **/
@Getter
@Setter
public class MobileMenuChangeModuleParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 模块 */
    @ApiModelProperty(value = "module", required = true)
    @NotBlank(message = "module不能为空")
    private String module;
}
