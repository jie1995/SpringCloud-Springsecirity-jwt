package com.microservice.cloud.authen.controller.sms;

import com.microservice.cloud.authen.security.validate.sms.SmsCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 微笑天使
 * @Date: 2018/12/15 16:19
 * @Version 1.0
 */
@RestController
//@RequestMapping("/auth")
public class ValidateController {

    @Autowired
    private SmsCodeProcessor smsValidateCodeProcessor;

    /**
     * 生成验证码
     * */
    @PostMapping("/generateCode")
    public void generateCode(HttpServletRequest request) throws Exception{
        smsValidateCodeProcessor.create(request);
    }
}
