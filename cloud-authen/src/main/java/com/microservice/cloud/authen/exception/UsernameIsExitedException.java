package com.microservice.cloud.authen.exception;

/**
 * @author weili
 * @describle 自定义UsernameIsExitedException，会在全局异常捕获该异常
 */
public class UsernameIsExitedException extends RuntimeException {

    public UsernameIsExitedException(String msg) {
        super(msg);
    }

    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }
}