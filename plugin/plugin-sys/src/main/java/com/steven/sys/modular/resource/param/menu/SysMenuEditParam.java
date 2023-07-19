package com.steven.sys.modular.resource.param.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 菜单编辑参数
 *
 **/
@Getter
@Setter
public class SysMenuEditParam {

    /** id */
    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 父id */
    @ApiModelProperty(value = "父id", required = true, position = 2)
    @NotBlank(message = "parentId不能为空")
    private String parentId;

    /** 标题 */
    @ApiModelProperty(value = "标题", required = true, position = 3)
    @NotBlank(message = "title不能为空")
    private String title;

    /** 菜单类型 */
    @ApiModelProperty(value = "菜单类型", required = true, position = 4)
    @NotBlank(message = "menuType不能为空")
    private String menuType;

    /** 模块 */
    @ApiModelProperty(value = "模块", required = true, position = 5)
    @NotBlank(message = "module不能为空")
    private String module;

    /** 路径 */
    @ApiModelProperty(value = "路径", required = true, position = 6)
    @NotBlank(message = "path不能为空")
    private String path;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 7)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 别名 */
    @ApiModelProperty(value = "别名", position = 8)
    private String name;

    /** 组件 */
    @ApiModelProperty(value = "组件", position = 9)
    private String component;

    /** 图标 */
    @ApiModelProperty(value = "图标", position = 10)
    private String icon;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 11)
    private String extJson;
}
