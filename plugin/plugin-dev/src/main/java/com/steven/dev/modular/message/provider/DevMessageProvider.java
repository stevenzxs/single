package com.steven.dev.modular.message.provider;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.steven.dev.api.DevMessageApi;
import com.steven.dev.modular.message.param.DevMessageIdParam;
import com.steven.dev.modular.message.param.DevMessageListParam;
import com.steven.dev.modular.message.param.DevMessageSendParam;
import com.steven.dev.modular.message.service.DevMessageService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 站内信API接口提供者
 **/
@Service
public class DevMessageProvider implements DevMessageApi {

    @Resource
    private DevMessageService devMessageService;

    @Override
    public void sendMessage(List<String> receiverIdList, String subject) {
        this.sendMessage(receiverIdList, subject, null);
    }

    @Override
    public void sendMessage(List<String> receiverIdList, String subject, String content) {
        this.sendMessageWithContent(receiverIdList, subject, null);
    }

    @Override
    public void sendMessageWithContent(List<String> receiverIdList, String subject, String content) {
        this.sendMessageWithContent(receiverIdList, null, subject, content);
    }

    @Override
    public void sendMessageWithContent(List<String> receiverIdList, String category, String subject, String content) {
        DevMessageSendParam devMessageSendParam = new DevMessageSendParam();
        devMessageSendParam.setReceiverIdList(receiverIdList);
        devMessageSendParam.setCategory(category);
        devMessageSendParam.setSubject(subject);
        devMessageSendParam.setContent(ObjectUtil.isEmpty(content)?subject:content);
        devMessageService.send(devMessageSendParam);
    }

    @Override
    public List<JSONObject> list(List<String> receiverIdList, Integer limit) {
        DevMessageListParam devMessageListParam = new DevMessageListParam();
        devMessageListParam.setReceiverIdList(receiverIdList);
        devMessageListParam.setLimit(limit);
        return devMessageService.list(devMessageListParam).stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }

    @Override
    public Page<JSONObject> page(List<String> receiverIdList, String category) {
        return devMessageService.page(receiverIdList, category);
    }

    @Override
    public JSONObject detail(String id) {
        DevMessageIdParam devMessageIdParam = new DevMessageIdParam();
        devMessageIdParam.setId(id);
        return JSONUtil.parseObj(devMessageService.detail(devMessageIdParam));
    }
}
