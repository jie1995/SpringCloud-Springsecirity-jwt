package com.microservice.cloud.authen.security.generator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: 微笑天使
 * @Date: 2018/12/1 16:26
 * @Version 1.0
 */
@Component
@Slf4j
public class JwtGenerator {

    public static final String SIGNING_KEY = "spring-config-@Jwt!&Secret^#";

    public String JwtGenerator(Authentication authentication) {
        String token = null;
        try {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
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
                    .setSubject(authentication.getName() + "-" + roleList)
                    // 设置过期时间24小时，设置过期时间应该短，防止别人拿到token发起请求
                    .setExpiration(time)
                    //算法可以自定，不一定非要采用HS512
                    .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                    .compact();
            log.info("TOKEN:{}", token);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
