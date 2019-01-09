package com.microservice.cloud.authen.security.validate;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码处理器，封装不通的验证码处理逻辑
 * @author
 */
public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     *
     * @param request
     * @throws Exception
     */
    void create(HttpServletRequest request) throws Exception;

    /**
     * 校验验证码
     * @param request
     * @throws Exception
     */
    void validate(HttpServletRequest request);

}
