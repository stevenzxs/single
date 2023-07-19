package com.steven.common.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 过滤器异常工具类，用于处理过滤器中的异常
 * 原理：将异常转发到/errorView进行处理
 *
 **/
public class CommonFilterExceptionUtil {

    /**
     * 处理过滤器中的异常
     *
     **/
    public static void handleFilterException(ServletRequest request, ServletResponse response, Exception e) {
        try {
            request.setAttribute("model", e);
            request.getRequestDispatcher("/errorView").forward(request, response);
        } catch (Exception ignored) {
        }
    }
}
