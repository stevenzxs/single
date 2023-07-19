package com.steven.common.utils.exception;

abstract  public class BusinessException extends Exception{
    private static final long serialVersionUID = 8684333269210029273L;
    private final String tips;

    public BusinessException(String tips){
        super(tips);
        this.tips = tips;
    }

    public BusinessException(String tips,String message){
        super(message);
        this.tips = tips;
    }

    public String getTips(){
        return tips;
    }
}
