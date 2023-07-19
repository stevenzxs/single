package com.steven.dev.modular.message.controller;

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
import com.steven.dev.modular.message.entity.DevMessage;
import com.steven.dev.modular.message.param.DevMessageIdParam;
import com.steven.dev.modular.message.param.DevMessagePageParam;
import com.steven.dev.modular.message.param.DevMessageSendParam;
import com.steven.dev.modular.message.result.DevMessageResult;
import com.steven.dev.modular.message.service.DevMessageService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 站内信控制器
 **/
@Api(tags = "站内信控制器")
@ApiSupport(author = "STEVEN_TEAM", order = 6)
@RestController
@Validated
public class DevMessageController {

    @Resource
    private DevMessageService devMessageService;

    /**
     * 发送站内信
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("发送站内信")
    @CommonLog("发送站内信")
    @PostMapping("/dev/message/send")
    public CommonResult<String> send(@RequestBody @Valid DevMessageSendParam devMessageSendParam) {
        devMessageService.send(devMessageSendParam);
        return CommonResult.ok();
    }

    /**
     * 获取站内信分页
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("获取站内信分页")
    @GetMapping("/dev/message/page")
    public CommonResult<Page<DevMessage>> page(DevMessagePageParam devMessagePageParam) {
        return CommonResult.data(devMessageService.page(devMessagePageParam));
    }

    /**
     * 删除站内信
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("删除站内信")
    @CommonLog("删除站内信")
    @PostMapping("/dev/message/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                               CommonValidList<DevMessageIdParam> devMessageIdParamList) {
        devMessageService.delete(devMessageIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取站内信详情
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("获取站内信详情")
    @GetMapping("/dev/message/detail")
    public CommonResult<DevMessageResult> detail(@Valid DevMessageIdParam devMessageIdParam) {
        return CommonResult.data(devMessageService.detail(devMessageIdParam));
    }
}
