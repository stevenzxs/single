package com.steven.mobile.modular.resource.provider;

import org.springframework.stereotype.Service;
import com.steven.mobile.api.MobileButtonApi;
import com.steven.mobile.modular.resource.entity.MobileButton;
import com.steven.mobile.modular.resource.service.MobileButtonService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 移动端按钮API接口提供者
 **/
@Service
public class MobileButtonApiProvider implements MobileButtonApi {

    @Resource
    private MobileButtonService mobileButtonService;

    @Override
    public List<String> listByIds(List<String> buttonIdList) {
        return mobileButtonService.listByIds(buttonIdList).stream().map(MobileButton::getCode).collect(Collectors.toList());
    }
}
