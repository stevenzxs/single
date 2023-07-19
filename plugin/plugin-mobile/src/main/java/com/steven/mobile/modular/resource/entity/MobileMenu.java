package com.steven.mobile.modular.resource.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.steven.common.pojo.CommonEntity;

/**
 * 移动端菜单实体
 **/
@Getter
@Setter
@TableName("MOBILE_RESOURCE")
public class MobileMenu extends CommonEntity {

    /** 主键 */
    @TableId
    @ApiModelProperty(value = "主键", position = 1)
    private String id;

    /** 父ID */
    @ApiModelProperty(value = "父ID", position = 2)
    private String parentId;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 3)
    private String title;

    /** 编码 */
    @ApiModelProperty(value = "编码", position = 4)
    private String code;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 5)
    private String category;

    /** 模块 */
    @ApiModelProperty(value = "模块", position = 6)
    private String module;

    /** 菜单类型 */
    @ApiModelProperty(value = "菜单类型", position = 7)
    private String menuType;

    /** 路径 */
    @ApiModelProperty(value = "路径", position = 8)
    private String path;

    /** 图标 */
    @ApiModelProperty(value = "图标", position = 9)
    private String icon;

    /** 颜色 */
    @ApiModelProperty(value = "颜色", position = 10)
    private String color;

    /** 规则类型 */
    @ApiModelProperty(value = "规则类型", position = 11)
    private String regType;

    /** 可用状态 */
    @ApiModelProperty(value = "可用状态", position = 12)
    private String status;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", position = 13)
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 14)
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String extJson;
}
