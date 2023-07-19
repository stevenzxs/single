package com.steven.sys.modular.position.provider;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;
import com.steven.sys.api.SysPositionApi;
import com.steven.sys.modular.position.param.SysPositionSelectorPositionParam;
import com.steven.sys.modular.position.service.SysPositionService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 职位API接口提供者
 *
 **/
@Service
public class SysPositionApiProvider implements SysPositionApi {

    @Resource
    private SysPositionService sysPositionService;

    @Override
    public String getNameById(String positionId) {
        return sysPositionService.queryEntity(positionId).getName();
    }

    @Override
    public List<JSONObject> positionSelector(String orgId, String searchKey) {
        SysPositionSelectorPositionParam sysPositionSelectorPositionParam = new SysPositionSelectorPositionParam();
        sysPositionSelectorPositionParam.setOrgId(orgId);
        sysPositionSelectorPositionParam.setSearchKey(searchKey);
        return sysPositionService.positionSelector(sysPositionSelectorPositionParam).stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }
}
