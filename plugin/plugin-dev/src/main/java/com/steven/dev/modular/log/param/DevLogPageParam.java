package com.steven.dev.modular.log.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 日志查询参数
 */
@Getter
@Setter
public class DevLogPageParam {

    /** 当前页 */
    @ApiModelProperty(value = "当前页码")
    private Integer current;

    /** 每页条数 */
    @ApiModelProperty(value = "每页条数")
    private Integer size;

    /** 排序字段 */
    @ApiModelProperty(value = "排序字段，字段驼峰名称，如：userName")
    private String sortField;

    /** 排序方式 */
    @ApiModelProperty(value = "排序方式，升序：ASCEND；降序：DESCEND")
    private String sortOrder;

    /** 日志分类 */
    @ApiModelProperty(value = "日志分类")
    private String category;

    /** 日志名称关键词 */
    @ApiModelProperty(value = "日志名称关键词")
    private String searchKey;
}
