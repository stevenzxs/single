
package com.steven.client.modular.user.controller;

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
import com.steven.client.modular.user.entity.ClientUser;
import com.steven.client.modular.user.param.ClientUserAddParam;
import com.steven.client.modular.user.param.ClientUserEditParam;
import com.steven.client.modular.user.param.ClientUserIdParam;
import com.steven.client.modular.user.param.ClientUserPageParam;
import com.steven.client.modular.user.service.ClientUserService;
import com.steven.common.annotation.CommonLog;
import com.steven.common.pojo.CommonResult;
import com.steven.common.pojo.CommonValidList;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * C端用户控制器
 **/
@Api(tags = "C端用户控制器")
@ApiSupport(author = "STEVEN_TEAM", order = 1)
@RestController
@Validated
public class ClientUserController {

    @Resource
    private ClientUserService clientUserService;

    /**
     * 获取C端用户分页
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取C端用户分页")
    @GetMapping("/client/user/page")
    public CommonResult<Page<ClientUser>> page(ClientUserPageParam clientUserPageParam) {
        return CommonResult.data(clientUserService.page(clientUserPageParam));
    }

    /**
     * 添加C端用户
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加C端用户")
    @CommonLog("添加C端用户")
    @PostMapping("/client/user/add")
    public CommonResult<String> add(@RequestBody @Valid ClientUserAddParam clientUserAddParam) {
        clientUserService.add(clientUserAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑C端用户
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑C端用户")
    @CommonLog("编辑C端用户")
    @PostMapping("/client/user/edit")
    public CommonResult<String> edit(@RequestBody @Valid ClientUserEditParam clientUserEditParam) {
        clientUserService.edit(clientUserEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除C端用户
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除C端用户")
    @CommonLog("删除C端用户")
    @PostMapping("/client/user/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<ClientUserIdParam> clientUserIdParamList) {
        clientUserService.delete(clientUserIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取C端用户详情
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取C端用户详情")
    @GetMapping("/client/user/detail")
    public CommonResult<ClientUser> detail(@Valid ClientUserIdParam clientUserIdParam) {
        return CommonResult.data(clientUserService.detail(clientUserIdParam));
    }
}
