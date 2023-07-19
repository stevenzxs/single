package com.steven.common.utils.result;

import lombok.Getter;

public enum ResStatus {

    SUCCESS(200,"请求成功"),
    FAIL(400,"处理失败"),
    ERROR401(401,"未登录"),
    ERROR403(403,"无权限"),
    ERROR404(404, "路径不存在"),
    ERROR405(405, "请求方法不正确"),
    ERROR415(415, "参数传递异常"),
    ERROR(500,"系统异常"),
    UNKOWN(900,"未知错误");

    private final Integer code;
    private final String desc;
    ResStatus(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
