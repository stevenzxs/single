package com.steven.dev.modular.config.controller;

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
import com.steven.dev.modular.config.entity.DevConfig;
import com.steven.dev.modular.config.enums.DevConfigCategoryEnum;
import com.steven.dev.modular.config.param.*;
import com.steven.dev.modular.config.service.DevConfigService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 配置控制器
 **/
@Api(tags = "配置控制器")
@ApiSupport(author = "STEVEN_TEAM", order = 1)
@RestController
@Validated
public class DevConfigController {

    @Resource
    private DevConfigService devConfigService;

    /**
     * 获取配置分页
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取配置分页")
    @GetMapping("/dev/config/page")
    public CommonResult<Page<DevConfig>> page(DevConfigPageParam devConfigPageParam) {
        return CommonResult.data(devConfigService.page(devConfigPageParam));
    }

    /**
     * 获取系统基础配置
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("获取系统基础配置")
    @GetMapping("/dev/config/sysBaseList")
    public CommonResult<List<DevConfig>> sysBaseList() {
        DevConfigListParam devConfigListParam = new DevConfigListParam();
        devConfigListParam.setCategory(DevConfigCategoryEnum.SYS_BASE.getValue());
        return CommonResult.data(devConfigService.list(devConfigListParam));
    }

    /**
     * 获取配置列表
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("获取配置列表")
    @GetMapping("/dev/config/list")
    public CommonResult<List<DevConfig>> list(DevConfigListParam devConfigListParam) {
        return CommonResult.data(devConfigService.list(devConfigListParam));
    }

    /**
     * 添加配置
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("添加配置")
    @CommonLog("添加配置")
    @PostMapping("/dev/config/add")
    public CommonResult<String> add(@RequestBody @Valid DevConfigAddParam devConfigAddParam) {
        devConfigService.add(devConfigAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑配置
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("编辑配置")
    @CommonLog("编辑配置")
    @PostMapping("/dev/config/edit")
    public CommonResult<String> edit(@RequestBody @Valid DevConfigEditParam devConfigEditParam) {
        devConfigService.edit(devConfigEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除配置
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("删除配置")
    @CommonLog("删除配置")
    @PostMapping("/dev/config/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                           CommonValidList<DevConfigIdParam> devConfigIdParamList) {
        devConfigService.delete(devConfigIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取配置详情
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation("获取配置详情")
    @GetMapping("/dev/config/detail")
    public CommonResult<DevConfig> detail(@Valid DevConfigIdParam devConfigIdParam) {
        return CommonResult.data(devConfigService.detail(devConfigIdParam));
    }

    /**
     * 配置批量更新
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation("配置批量更新")
    @CommonLog("配置批量更新")
    @PostMapping("/dev/config/editBatch")
    public CommonResult<String> editBatch(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                      CommonValidList<DevConfigBatchParam> devConfigBatchParamList) {
        devConfigService.editBatch(devConfigBatchParamList);
        return CommonResult.ok();
    }
}
