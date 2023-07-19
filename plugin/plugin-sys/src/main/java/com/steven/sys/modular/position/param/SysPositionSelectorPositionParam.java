package com.steven.sys.modular.position.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 职位选择器参数
 *
 **/
@Getter
@Setter
public class SysPositionSelectorPositionParam {

    /** 组织id */
    @ApiModelProperty(value = "组织id")
    private String orgId;

    /** 名称关键词 */
    @ApiModelProperty(value = "名称关键词")
    private String searchKey;
}
