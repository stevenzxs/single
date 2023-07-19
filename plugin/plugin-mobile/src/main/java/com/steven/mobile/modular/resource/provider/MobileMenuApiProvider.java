package com.steven.mobile.modular.resource.provider;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Service;
import com.steven.mobile.api.MobileMenuApi;
import com.steven.mobile.modular.resource.service.MobileMenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 移动端菜单API接口提供者
 **/
@Service
public class MobileMenuApiProvider implements MobileMenuApi {

    @Resource
    private MobileMenuService mobileMenuService;

    @Override
    public List<JSONObject> mobileMenuTreeSelector() {
        return mobileMenuService.mobileMenuTreeSelector();
    }

    @Override
    public List<Tree<String>> loginMobileMenuTree(List<String> menuIdList) {
        return mobileMenuService.loginMobileMenuTree(menuIdList);
    }
}
