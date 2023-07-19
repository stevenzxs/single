package com.steven.sys.modular.resource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.steven.common.annotation.CommonLog;
import com.steven.common.pojo.CommonResult;
import com.steven.common.pojo.CommonValidList;
import com.steven.sys.modular.resource.entity.SysSpa;
import com.steven.sys.modular.resource.param.spa.SysSpaAddParam;
import com.steven.sys.modular.resource.param.spa.SysSpaEditParam;
import com.steven.sys.modular.resource.param.spa.SysSpaIdParam;
import com.steven.sys.modular.resource.param.spa.SysSpaPageParam;
import com.steven.sys.modular.resource.service.SysSpaService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 单页面控制器
 *
 **/
@Api(tags = "单页面控制器")
@ApiSupport(author = "STEVEN_TEAM", order = 7)
@RestController
@Validated
public class SysSpaController {

    @Resource
    private SysSpaService sysSpaService;

    /**
     * 获取单页面分页
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取单页面分页")
    @GetMapping("/sys/spa/page")
    public CommonResult<Page<SysSpa>> page(SysSpaPageParam sysSpaPageParam) {
        return CommonResult.data(sysSpaService.page(sysSpaPageParam));
    }

    /**
     * 添加单页面
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加单页面")
    @CommonLog("添加单页面")
    @PostMapping("/sys/spa/add")
    public CommonResult<String> add(@RequestBody @Valid SysSpaAddParam sysSpaAddParam) {
        sysSpaService.add(sysSpaAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑单页面
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑单页面")
    @CommonLog("编辑单页面")
    @PostMapping("/sys/spa/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysSpaEditParam sysSpaEditParam) {
        sysSpaService.edit(sysSpaEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除单页面
     *
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除单页面")
    @CommonLog("删除单页面")
    @PostMapping("/sys/spa/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<SysSpaIdParam> sysSpaIdParamList) {
        sysSpaService.delete(sysSpaIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取单页面详情
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取单页面详情")
    @GetMapping("/sys/spa/detail")
    public CommonResult<SysSpa> detail(@Valid SysSpaIdParam sysSpaIdParam) {
        return CommonResult.data(sysSpaService.detail(sysSpaIdParam));
    }
}
