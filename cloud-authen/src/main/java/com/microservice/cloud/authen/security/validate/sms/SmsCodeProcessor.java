package com.microservice.cloud.authen.security.validate.sms;


import com.microservice.cloud.authen.security.validate.impl.AbstractValidateCodeProcessor;
import com.microservice.cloud.authen.security.validate.vo.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/1/10.
 *
 * @author zlf
 * @since 1.0
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(String phoneNum, ValidateCode validateCode) throws Exception {
        smsCodeSender.send(phoneNum, validateCode.getCode());
    }
}
