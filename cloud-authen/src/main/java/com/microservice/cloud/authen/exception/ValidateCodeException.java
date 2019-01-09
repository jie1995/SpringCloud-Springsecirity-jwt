package com.microservice.cloud.authen.exception;

import lombok.Data;
import org.springframework.security.core.AuthenticationException;

/**
 * @author weili
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = -7525757620869234981L;

    private Integer code;

    public ValidateCodeException(String msg, Throwable t, Integer code) {
        super(msg, t);
        this.code = code;

    }

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public ValidateCodeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
