package com.microservice.cloud.authen.security.filter;

import com.microservice.cloud.common.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，在这个方法里生成token。
 * @author 微笑天使
 */
@Slf4j
public class JWTLoginFilter extends MyUsernamePasswordAuthenticationFilter {

    public static final String SIGNING_KEY = "spring-config-@Jwt!&Secret^#";

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**接收用户信息准备生成用户凭证*/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        log.info("===接收用户信息准备生成用户凭证===");
        /*try {*/
            //User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            User user=new User();
            user.setUserName("weili");
            user.setUserPwd("chengzi19950922");
            log.info("待验证user:{}",user);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUserName(),
                            user.getUserPwd(),
                            new ArrayList<>())
            );
       /* } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    /** 用户成功登录后，这个方法会被调用，这个方法里生成token*/
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
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
            log.info("将TOKEN写入header成功...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
