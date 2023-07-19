package com.steven.mobile.modular.resource.controller;

import cn.hutool.core.lang.tree.Tree;
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
import com.steven.mobile.modular.resource.entity.MobileMenu;
import com.steven.mobile.modular.resource.entity.MobileModule;
import com.steven.mobile.modular.resource.param.menu.*;
import com.steven.mobile.modular.resource.service.MobileMenuService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 移动端菜单控制器
 */
@Api(tags = "移动端菜单控制器")
@ApiSupport(author = "STEVEN_TEAM", order = 1)
@RestController
@Validated
public class MobileMenuController {

    @Resource
    private MobileMenuService mobileMenuService;

    /**
     * 获取移动端菜单tree
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取移动端菜单tree")
    @GetMapping("/mobile/menu/tree")
    public CommonResult<List<Tree<String>>> tree(MobileMenuTreeParam mobileMenuTreeParam) {
        return CommonResult.data(mobileMenuService.tree(mobileMenuTreeParam));
    }

    /**
     * 添加移动端菜单
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加移动端菜单")
    @CommonLog("添加移动端菜单")
    @PostMapping("/mobile/menu/add")
    public CommonResult<String> add(@RequestBody @Valid MobileMenuAddParam mobileMenuAddParam) {
        mobileMenuService.add(mobileMenuAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑移动端菜单
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑移动端菜单")
    @CommonLog("编辑移动端菜单")
    @PostMapping("/mobile/menu/edit")
    public CommonResult<String> edit(@RequestBody @Valid MobileMenuEditParam mobileMenuEditParam) {
        mobileMenuService.edit(mobileMenuEditParam);
        return CommonResult.ok();
    }

    /**
     * 更改移动端菜单所属模块
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("更改移动端菜单所属模块")
    @CommonLog("更改移动端菜单所属模块")
    @PostMapping("/mobile/menu/changeModule")
    public CommonResult<String> changeModule(@RequestBody @Valid MobileMenuChangeModuleParam mobileMenuChangeModuleParam) {
        mobileMenuService.changeModule(mobileMenuChangeModuleParam);
        return CommonResult.ok();
    }

    /**
     * 删除移动端菜单
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除移动端菜单")
    @CommonLog("删除移动端菜单")
    @PostMapping("/mobile/menu/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<MobileMenuIdParam> mobileMenuIdParamList) {
        mobileMenuService.delete(mobileMenuIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取移动端菜单详情
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取移动端菜单详情")
    @GetMapping("/mobile/menu/detail")
    public CommonResult<MobileMenu> detail(@Valid MobileMenuIdParam mobileMenuIdParam) {
        return CommonResult.data(mobileMenuService.detail(mobileMenuIdParam));
    }

    /* ====菜单部分所需要用到的选择器==== */

    /**
     * 获取模块选择器
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation("获取模块选择器")
    @GetMapping("/mobile/menu/moduleSelector")
    public CommonResult<List<MobileModule>> moduleSelector(MobileMenuSelectorModuleParam mobileMenuSelectorModuleParam) {
        return CommonResult.data(mobileMenuService.moduleSelector(mobileMenuSelectorModuleParam));
    }

    /**
     * 获取菜单树选择器
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation("获取菜单树选择器")
    @GetMapping("/mobile/menu/menuTreeSelector")
    public CommonResult<List<Tree<String>>> menuTreeSelector(MobileMenuSelectorMenuParam mobileMenuSelectorMenuParam) {
        return CommonResult.data(mobileMenuService.menuTreeSelector(mobileMenuSelectorMenuParam));
    }
}
