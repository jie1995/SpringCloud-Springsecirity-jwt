package com.microservice.cloud.authen.security.validate.sms;

import org.springframework.stereotype.Component;

/**
 * 短信发送接口
 * @author zlf
 * @since 1.0
 */
@Component
public interface SmsCodeSender {

    /**
     * 发送短信验证码
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);
}
