package com.steven.mobile.modular.resource.controller;

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
import com.steven.mobile.modular.resource.entity.MobileButton;
import com.steven.mobile.modular.resource.param.button.MobileButtonAddParam;
import com.steven.mobile.modular.resource.param.button.MobileButtonEditParam;
import com.steven.mobile.modular.resource.param.button.MobileButtonIdParam;
import com.steven.mobile.modular.resource.param.button.MobileButtonPageParam;
import com.steven.mobile.modular.resource.service.MobileButtonService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 移动端按钮控制器
 **/
@Api(tags = "移动端按钮控制器")
@ApiSupport(author = "STEVEN_TEAM", order = 3)
@RestController
@Validated
public class MobileButtonController {

    @Resource
    private MobileButtonService mobileButtonService;

    /**
     * 获取移动端按钮分页
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取移动端按钮分页")
    @GetMapping("/mobile/button/page")
    public CommonResult<Page<MobileButton>> page(MobileButtonPageParam mobileButtonPageParam) {
        return CommonResult.data(mobileButtonService.page(mobileButtonPageParam));
    }

    /**
     * 添加移动端按钮
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加移动端按钮")
    @CommonLog("添加移动端按钮")
    @PostMapping("/mobile/button/add")
    public CommonResult<String> add(@RequestBody @Valid MobileButtonAddParam mobileButtonAddParam) {
        mobileButtonService.add(mobileButtonAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑移动端按钮
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑移动端按钮")
    @CommonLog("编辑移动端按钮")
    @PostMapping("/mobile/button/edit")
    public CommonResult<String> edit(@RequestBody @Valid MobileButtonEditParam mobileButtonEditParam) {
        mobileButtonService.edit(mobileButtonEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除移动端按钮
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除移动端按钮")
    @CommonLog("删除移动端按钮")
    @PostMapping("/mobile/button/delete")
    public CommonResult<String> delete(@RequestBody @Valid List<MobileButtonIdParam> mobileButtonIdParamList) {
        mobileButtonService.delete(mobileButtonIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取移动端按钮详情
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取移动端按钮详情")
    @GetMapping("/mobile/button/detail")
    public CommonResult<MobileButton> detail(@Valid MobileButtonIdParam mobileButtonIdParam) {
        return CommonResult.data(mobileButtonService.detail(mobileButtonIdParam));
    }
}
