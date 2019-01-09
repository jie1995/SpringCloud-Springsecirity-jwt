package com.microservice.cloud.authen.security.filter;

import com.microservice.cloud.authen.security.generator.JwtGenerator;
import com.microservice.cloud.authen.security.mobile.SmsCodeAuthenticationToken;
import com.microservice.cloud.authen.security.properties.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 短信登录过滤器
 * @author
 */
@Slf4j
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


   @Autowired
   private JwtGenerator jwtGenerator;

    public static final String SIGNING_KEY = "spring-config-@Jwt!&Secret^#";

   /**
     * request中必须含有mobile参数
     * mobile
     */
    private String mobileParameter = SecurityConstants.DEFAULT_PHONE_PARAMETER;
    /**
     * post请求
     */
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        /**
         * 处理的手机验证码登录请求处理url
         */
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("手机号验证码验证登录...");
        //判断是是不是post请求
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //从请求中获取手机号码
        String mobile = obtainMobile(request);

        if (mobile == null) {
            mobile = "";
        }

        mobile = mobile.trim();
        //创建SmsCodeAuthenticationToken(未认证)
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

        //设置用户信息
        setDetails(request, authRequest);
        //返回Authentication实例
        return this.getAuthenticationManager().authenticate(authRequest);
    }


   @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
       // builder the token
       String token = null;
       try {
           Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
           // 定义存放角色集合的对象
           List roleList = new ArrayList<>();
           for (GrantedAuthority grantedAuthority : authorities) {
               roleList.add(grantedAuthority.getAuthority());
           }
           // 设置过期时间
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(new Date());
           calendar.add(Calendar.HOUR, 24);
           Date time = calendar.getTime();
           token = Jwts.builder()
                   .setSubject(auth.getName() + "-" + roleList)
                   // 设置过期时间24小时，设置过期时间应该短，防止别人拿到token发起请求
                   .setExpiration(time)
                   //算法可以自定，不一定非要采用HS512
                   .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                   .compact();
           log.info("TOKEN:{}",token);
           // 登录成功后，返回token到header里，再次请求时作为请求头
           response.addHeader("Authorization", "Bearer " + token);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    /**
     * 获取手机号
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

    }

    public void setMobileParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.mobileParameter = usernameParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return mobileParameter;
    }
}
