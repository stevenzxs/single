package com.steven.common.util;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.steven.common.exception.CommonException;

/**
 * 通用邮件工具类
 *
 **/
public class CommonEmailUtil {

    /**
     * 判断是否邮箱
     *
     **/
    public static boolean isEmail(String email) {
        return ReUtil.isMatch("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", email);
    }

    /**
     * 校验邮箱格式
     *
     **/
    public static void validEmail(String emails) {
        StrUtil.split(emails, StrUtil.COMMA).forEach(email -> {
            if(!ReUtil.isMatch("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", email)) {
                throw new CommonException("邮件地址：{}格式错误", email);
            }
        });
    }
}
