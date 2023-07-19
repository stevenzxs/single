package com.steven.auth.modular.third.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhyd.oauth.model.AuthCallback;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.steven.auth.modular.third.entity.AuthThirdUser;
import com.steven.auth.modular.third.param.AuthThirdCallbackParam;
import com.steven.auth.modular.third.param.AuthThirdRenderParam;
import com.steven.auth.modular.third.param.AuthThirdUserPageParam;
import com.steven.auth.modular.third.result.AuthThirdRenderResult;
import com.steven.auth.modular.third.service.AuthThirdService;
import com.steven.common.pojo.CommonResult;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 第三方登录控制器
 **/
@Api(tags = "三方登录控制器")
@ApiSupport(author = "STEVEN_TEAM", order = 5)
@RestController
@Validated
public class AuthThirdController {

    @Resource
    private AuthThirdService authThirdService;

    /**
     * 第三方登录页面渲染
     **/
    @ApiOperationSupport(order = 1)
    @ApiOperation("第三方登录页面渲染")
    @GetMapping("/auth/third/render")
    public CommonResult<AuthThirdRenderResult> render(@Valid AuthThirdRenderParam authThirdRenderParam) {
        return CommonResult.data(authThirdService.render(authThirdRenderParam));
    }

    /**
     * 第三方登录授权回调
     **/
    @ApiOperationSupport(order = 2)
    @ApiOperation("第三方登录授权回调")
    @GetMapping("/auth/third/callback")
    public CommonResult<String> callback(@Valid AuthThirdCallbackParam authThirdCallbackParam, AuthCallback authCallback) {
        return CommonResult.data(authThirdService.callback(authThirdCallbackParam, authCallback));
    }

    /**
     * 获取三方用户分页
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("获取三方用户分页")
    @GetMapping("/auth/third/page")
    public CommonResult<Page<AuthThirdUser>> page(AuthThirdUserPageParam authThirdUserPageParam) {
        return CommonResult.data(authThirdService.page(authThirdUserPageParam));
    }
}
