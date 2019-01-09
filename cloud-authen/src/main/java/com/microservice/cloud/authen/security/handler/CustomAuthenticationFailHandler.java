package com.microservice.cloud.authen.security.handler;

import com.microservice.cloud.common.message.TradeMessages;
import com.microservice.cloud.common.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: 登陆失败处理器
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/11/28 9:06
 */
@Component
public class CustomAuthenticationFailHandler implements AuthenticationFailureHandler {

    private  Logger logger= LoggerFactory.getLogger(CustomAuthenticationFailHandler.class);
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("认证失败，进行业务逻辑处理");
        TradeMessages<String> messages=new TradeMessages<>();
        messages.setResultMessage(exception.getMessage());
        messages.setResultCode("500");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONUtils.toJSON(messages));
    }
}
