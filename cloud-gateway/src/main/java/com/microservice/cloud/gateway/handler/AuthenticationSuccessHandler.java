package com.microservice.cloud.gateway.handler;


import com.microservice.cloud.common.message.TradeMessages;
import com.microservice.cloud.common.utils.JSONUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * TODO: 登陆成功处理器
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/11/28 9:06
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    /**
     * 登陆成功后会被调用
     * @param request
     * @param response
     * @param authentication
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        logger.info("认证成功，进行业务逻辑处理");
        TradeMessages<Authentication> messages=new TradeMessages<>();

        messages.setResultMessage("200");
        messages.setToken(UUID.randomUUID().toString());
        messages.setResultMessage("认证成功，返回token");
        //设置用户相关信息
        messages.setData(authentication);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONUtils.toJSON(messages));
    }
}
