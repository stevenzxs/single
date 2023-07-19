package com.steven.auth.modular.monitor.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steven.auth.modular.monitor.param.AuthExitSessionParam;
import com.steven.auth.modular.monitor.param.AuthExitTokenParam;
import com.steven.auth.modular.monitor.param.AuthSessionPageParam;
import com.steven.auth.modular.monitor.result.AuthSessionAnalysisResult;
import com.steven.auth.modular.monitor.result.AuthSessionPageResult;

import java.util.List;

/**
 * 会话治理Service接口
 **/
public interface AuthSessionService {

    /**
     * 会话统计
     **/
    AuthSessionAnalysisResult analysis();

    /**
     * 查询B端会话
     */
    Page<AuthSessionPageResult> pageForB(AuthSessionPageParam authSessionPageParam);

    /**
     * 查询C端会话
     */
    Page<AuthSessionPageResult> pageForC(AuthSessionPageParam authSessionPageParam);

    /**
     * 强退B端会话
     */
    void exitSessionForB(List<AuthExitSessionParam> authExitSessionParamList);

    /**
     * 强退C端会话
     */
    void exitSessionForC(List<AuthExitSessionParam> authExitSessionParamList);

    /**
     * 强退B端token
     */
    void exitTokenForB(List<AuthExitTokenParam> authExitTokenParamList);

    /**
     * 强退C端token
     */
    void exitTokenForC(List<AuthExitTokenParam> authExitTokenParamList);
}
