package com.steven.common.utils.exception;

import com.steven.common.utils.result.ResStatus;

@ExceptionStatus(status= ResStatus.ERROR401,description = "未登录")
public class NeverLoginException extends BusinessException{
    private static final long serialVersionUID = -8380443084367425479L;
    public NeverLoginException(String tips){
        super(tips);
    }
    public NeverLoginException(String tips,String message){
        super(tips,message);
    }
}
