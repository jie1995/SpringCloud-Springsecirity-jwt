package com.microservice.cloud.authen.security.validate.sms;


import com.microservice.cloud.authen.security.properties.SecurityConstants;
import com.microservice.cloud.authen.security.validate.vo.ValidateCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @author 微笑天使
 * @since 1.0
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator {

    public ValidateCode generate() {
        String code = RandomStringUtils.randomNumeric(SecurityConstants.LENGTH);
        return new ValidateCode(code, SecurityConstants.EXPIRE_TIME);
    }
}
