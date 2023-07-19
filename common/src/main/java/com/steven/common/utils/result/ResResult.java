package com.steven.common.utils.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResResult<T> implements Serializable {
    private static final long serialVersionUID = -2540764645447402367L;
    private Integer code;
    private String msg;
    private String timestamp;
    private T data;

    public static ResResult ok(){
        ResResult result = new ResResult();
        result.code = ResStatus.SUCCESS.getCode();
        result.msg = ResStatus.SUCCESS.getDesc();
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        return result;
    }

    public static ResResult success(Object data){
        ResResult result = new ResResult();
        result.code = ResStatus.SUCCESS.getCode();
        result.msg = ResStatus.SUCCESS.getDesc();
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        result.data = data;
        return result;
    }

    public static ResResult success(ResStatus status, String msg, Object data ){
        ResResult result = new ResResult();
        result.code = ResStatus.SUCCESS.getCode();
        result.msg = msg;
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        result.data = data;
        return result;
    }

    public static ResResult error(String msg){
        ResResult result = new ResResult();
        result.code = ResStatus.ERROR.getCode();
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        result.msg = msg;
        return result;
    }
    public static ResResult error(ResStatus status, String msg){
        ResResult result = new ResResult();
        result.code = status.getCode();
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        result.msg = msg;
        return result;
    }

    public static ResResult info(ResStatus status, Object data){
        ResResult result = new ResResult();
        result.code = status.getCode();
        result.msg = status.getDesc();
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        result.data = data;
        return result;
    }

    public static ResResult info(ResStatus status,String msg, Object data){
        ResResult result = new ResResult();
        result.code = status.getCode();
        result.timestamp = String.valueOf(Instant.now().toEpochMilli());
        result.msg = msg;
        result.data = data;
        return result;
    }

    public void setCode(Integer code){
        this.code = code;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public void setData(T data){
        this.data = data;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

    public Object getData(){
        return data;
    }

    public String getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    /**
     * 响应状态码集合
     **/
    public static List<ResponseMessage> responseList() {
        return Arrays.stream(ResStatus.values()).map(ResStatus -> new ResponseMessageBuilder()
                        .code(ResStatus.getCode()).message(ResStatus.getDesc()).build())
                .collect(Collectors.toList());
    }
}
