package com.microservice.cloud.authen.security.validate.vo;

/**
 * @Author: 微笑天使
 * @Date: 2018/12/17 14:47
 * @Version 1.0
 */
public enum ResultEnum {
    CODE_ERROT(10501,"验证码验证失败");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
