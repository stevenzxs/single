package com.steven.biz.modular.org.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 人员选择器参数
 **/
@Getter
@Setter
public class BizOrgSelectorUserParam {

    /** 机构id */
    @ApiModelProperty(value = "机构id")
    private String orgId;

    /** 姓名关键词 */
    @ApiModelProperty(value = "姓名关键词")
    private String searchKey;
}
