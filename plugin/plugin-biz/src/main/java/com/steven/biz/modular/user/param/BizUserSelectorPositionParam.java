package com.steven.biz.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 岗位选择器参数
 **/
@Getter
@Setter
public class BizUserSelectorPositionParam {

    /** 机构id */
    @ApiModelProperty(value = "机构id")
    private String orgId;

    /** 名称关键词 */
    @ApiModelProperty(value = "名称关键词")
    private String searchKey;
}
