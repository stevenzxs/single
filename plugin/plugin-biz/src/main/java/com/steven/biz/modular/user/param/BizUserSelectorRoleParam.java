package com.steven.biz.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色选择器参数
 **/
@Getter
@Setter
public class BizUserSelectorRoleParam {

    /** 组织id */
    @ApiModelProperty(value = "组织id", position = 1)
    private String orgId;

    /** 角色分类 */
    @ApiModelProperty(value = "角色分类")
    private String category;

    /** 名称关键词 */
    @ApiModelProperty(value = "名称关键词")
    private String searchKey;
}
