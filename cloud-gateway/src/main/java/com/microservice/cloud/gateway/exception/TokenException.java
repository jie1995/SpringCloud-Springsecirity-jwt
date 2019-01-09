package com.microservice.cloud.gateway.exception;

/**
 * @author 微笑天使
 * @describle 自定义token异常，会在全局异常捕获该异常
 */

public class TokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenException(String message) {
        super(message);
    }
}
