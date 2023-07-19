package com.steven.biz.modular.org.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 机构编辑参数
 **/
@Getter
@Setter
public class BizOrgEditParam {

    /** id */
    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 父id */
    @ApiModelProperty(value = "父id", required = true, position = 2)
    @NotBlank(message = "parentId不能为空")
    private String parentId;

    /** 名称 */
    @ApiModelProperty(value = "名称", required = true, position = 3)
    @NotBlank(message = "name不能为空")
    private String name;

    /** 分类 */
    @ApiModelProperty(value = "分类", required = true, position = 4)
    @NotBlank(message = "category不能为空")
    private String category;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 5)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 主管id */
    @ApiModelProperty(value = "主管id", position = 6)
    private String directorId;

    /** 扩展JSON */
    @ApiModelProperty(value = "扩展JSON", position = 7)
    private String extJson;
}
