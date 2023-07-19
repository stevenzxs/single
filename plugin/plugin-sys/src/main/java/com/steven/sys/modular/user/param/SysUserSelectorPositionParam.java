package com.steven.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 职位选择器参数
 **/
@Getter
@Setter
public class SysUserSelectorPositionParam {

    /** 组织id */
    @ApiModelProperty(value = "组织id")
    private String orgId;

    /** 名称关键词 */
    @ApiModelProperty(value = "名称关键词")
    private String searchKey;
}
