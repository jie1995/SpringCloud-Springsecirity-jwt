package com.microservice.cloud.authen.security.validate.impl;

/*
import com.maomiyibian.microservice.customer.security.validate.ValidateCode;
import com.maomiyibian.microservice.customer.security.validate.ValidateCodeRepository;
import com.maomiyibian.microservice.customer.security.validate.ValidateCodeType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

*/
/**
 * Created on 2018/1/10.
 *
 * @author zlf
 * @since 1.0
 *//*

@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    */
/**
     * 验证码放入session时的前缀
     *//*

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    */
/**
     * 操作session得工具类
     *//*

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(request, validateCodeType), code);
    }


    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(request, validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request, codeType));
    }

    */
/**
     * 构建验证码翻入session得可以
     *
     * @return
     *//*

    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toLowerCase();
    }
}*/
