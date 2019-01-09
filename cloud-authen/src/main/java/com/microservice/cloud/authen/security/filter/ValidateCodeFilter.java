package com.microservice.cloud.authen.security.filter;

import com.microservice.cloud.authen.exception.ValidateCodeException;
import com.microservice.cloud.authen.security.properties.SecurityConstants;
import com.microservice.cloud.authen.security.validate.ValidateCodeProcessorHolder;
import com.microservice.cloud.authen.security.validate.ValidateCodeType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author
 * @since 1.0
 */
@Component("validateCodeFilter")
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 系统配置信息
     */
   /* @Autowired
    private SecurityProperties securityProperties;*/

    /**
     * 校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 存放所有需要检验验证码得url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 验证请求url与配置得url是否匹配得工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化bena完成后在家该信息也就是拦截得url
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        //在这里添加url和对应的校验类型
        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        urlMap.put(SecurityConstants.DEFAULT_REGISTER_URL, ValidateCodeType.SMS);
        //读取配置中的url配置
         //addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
    }

    /**
     * 系统中配置的需要校验验证码的URL根据校验的类型放入map
     *                   protected void addUrlToMap(String urlString, ValidateCodeType type) {
     *                  if (StringUtils.isNotBlank(urlString)) {
     *                  String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
     *                  for (String url : urls) {
     *                  urlMap.put(url, type);
     *                  }
     *                  }
     *                  }
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(request);
                logger.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }
        //验证通过后，authenticated身份
        filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        //限定post请求
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        log.info("该请求是否需要验证码拦截？为空则没有配置该拦截:{},", result);
        return result;
    }
}
