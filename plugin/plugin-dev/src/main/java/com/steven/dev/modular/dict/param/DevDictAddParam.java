package com.steven.dev.modular.dict.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 字典添加参数
 */
@Getter
@Setter
public class DevDictAddParam {

    /** 父id */
    @ApiModelProperty(value = "父id", position = 1)
    @NotBlank(message = "parentId不能为空")
    private String parentId;

    /** 字典文字 */
    @ApiModelProperty(value = "字典文字", position = 2)
    @NotBlank(message = "dictLabel不能为空")
    private String dictLabel;

    /** 字典值 */
    @ApiModelProperty(value = "字典值", position = 3)
    @NotBlank(message = "dictValue不能为空")
    private String dictValue;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 4)
    @NotBlank(message = "category不能为空")
    private String category;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", position = 5)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 6)
    private String extJson;
}
