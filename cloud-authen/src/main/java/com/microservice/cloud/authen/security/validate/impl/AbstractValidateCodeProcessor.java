package com.microservice.cloud.authen.security.validate.impl;

import com.microservice.cloud.authen.exception.ValidateCodeException;
import com.microservice.cloud.authen.security.properties.SecurityConstants;
import com.microservice.cloud.authen.security.service.imp.RedisService;
import com.microservice.cloud.authen.security.validate.ValidateCodeProcessor;
import com.microservice.cloud.authen.security.validate.ValidateCodeType;
import com.microservice.cloud.authen.security.validate.sms.SmsCodeGenerator;
import com.microservice.cloud.authen.security.validate.vo.ResultEnum;
import com.microservice.cloud.authen.security.validate.vo.ValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 微笑天使
 */
@Slf4j
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private RedisService redisService;


    @Autowired
    private SmsCodeGenerator smsValidateCodeGenerator;


    /**
     * 创建校验码，保存到redis,发送到手机
     */
    @Override
    public void create(HttpServletRequest request) throws Exception {
        ValidateCode validateCode = smsValidateCodeGenerator.generate();
        String phoneNum = request.getParameter("id");
        save(phoneNum, validateCode);
        send(phoneNum, validateCode);
    }

    /**
     * 根据Holder返回得CodeProcessor类型来获取验证码的类型
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }


    /**
     * 保存验证码
     */
    private void save(String phoneNum, ValidateCode validateCode) {
        redisService.hset(SecurityConstants.SMS_CODE_KEY, phoneNum, validateCode.getCode(), SecurityConstants.EXPIRE_TIME);
    }

    /**
     * 发送校验码，由子类实现
     * @param phoneNum
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(String phoneNum, ValidateCode validateCode) throws Exception;

    @SuppressWarnings("unchecked")
    @Override
    public void validate(HttpServletRequest request) {
        //todo 获取请求类型，后面删除该步骤
        ValidateCodeType codeType = getValidateCodeType(request);

        String phoneNum = request.getParameter("id");
        //从缓存中获取code，与请求的code对比。注意缓存过期后，会主动驱逐该键
        String codeInRedis =String.valueOf(redisService.hget(SecurityConstants.SMS_CODE_KEY,phoneNum));
        log.info("缓存中的的验证码:{}",codeInRedis);
        String codeInRequest;
        try {
            //获取请求进来的code
            codeInRequest = ServletRequestUtils.getStringParameter(request,
                    codeType.getParamNameOnValidate());
            log.info("请求中的验证码smsCode:{}", codeInRequest);
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException(ResultEnum.CODE_ERROT.getCode(), "获取验证码的值失败");
        }
        //开始验证
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(ResultEnum.CODE_ERROT.getCode(), codeType + "验证码的值不能为空");
        }
        //缓存中的code码
        if (codeInRedis == null||"null".equals(codeInRedis)) {
            throw new ValidateCodeException(ResultEnum.CODE_ERROT.getCode(), codeType + "验证码不存在");
        }

        if (!StringUtils.equals(codeInRedis, codeInRequest)) {
            throw new ValidateCodeException(ResultEnum.CODE_ERROT.getCode(), codeType + "验证码不匹配");
        }
        redisService.hdel(SecurityConstants.SMS_CODE_KEY,phoneNum,codeInRedis);
    }
}
