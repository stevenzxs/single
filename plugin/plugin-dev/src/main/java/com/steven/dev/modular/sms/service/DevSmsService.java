package com.steven.dev.modular.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.dev.modular.sms.entity.DevSms;
import com.steven.dev.modular.sms.param.DevSmsIdParam;
import com.steven.dev.modular.sms.param.DevSmsPageParam;
import com.steven.dev.modular.sms.param.DevSmsSendAliyunParam;
import com.steven.dev.modular.sms.param.DevSmsSendTencentParam;

import java.util.List;

/**
 * 短信Service接口
 **/
public interface DevSmsService extends IService<DevSms> {

    /**
     * 发送短信——阿里云
     **/
    void sendAliyun(DevSmsSendAliyunParam devSmsSendAliyunParam);

    /**
     * 发送短信——腾讯云
     **/
    void sendTencent(DevSmsSendTencentParam devSmsSendTencentParam);

    /**
     * 获取短信分页
     */
    Page<DevSms> page(DevSmsPageParam devSmsPageParam);

    /**
     * 删除短信
     **/
    void delete(List<DevSmsIdParam> devSmsIdParamList);

    /**
     * 获取短信详情
     */
    DevSms detail(DevSmsIdParam devSmsIdParam);

    /**
     * 获取短信详情
     */
    DevSms queryEntity(String id);
}
