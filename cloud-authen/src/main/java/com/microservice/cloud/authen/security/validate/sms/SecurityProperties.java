package com.microservice.cloud.authen.security.validate.sms;

import com.microservice.cloud.authen.security.properties.ValidateCodeProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author
 */

@Component
public class SecurityProperties {


    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     * 记住我的有效时间秒
     */
    private int rememberMeSeconds = 60 * 60 * 24 * 7;


    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
