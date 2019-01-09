package com.microservice.cloud.gateway.filter;

import com.microservice.cloud.authen.security.authority.GrantedAuthorityImpl;
import com.microservice.cloud.gateway.exception.TokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @description 自定义JWT认证过滤器
 * @what 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * @attention 此处filter继承OncePerRequestFilter，因为filter会被自动注入serverlet中，我们使用的时候又会注入到spring boot容器中，会导致filter执行两次
 * @author weili
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public static final String SIGNING_KEY = "spring-config-@Jwt!&Secret^#";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        log.info("开始验证权限...获取TOKEN：{}",header);
        if (header == null || !header.startsWith("Bearer ")) {
            //TODO redis中验证TOKEN
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new TokenException("Token为空");
        }
        String user = null;
        try {
            user = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody()
                    .getSubject();
            long end = System.currentTimeMillis();
            log.info("根据解析TOKEN对应的用户:{}",user  );
            log.info("执行时间: "+(end - start) + " 毫秒");
            if (user != null) {
                // String[] split = user.split("-")[1].split(",");这个split后:[role_ADMIN,所以后面比较权限会拒绝访问,做出修改
                String authoritiesString = user.split("-")[1];
                authoritiesString= StringUtils.strip(authoritiesString, "[]");
                String[] split=authoritiesString.split(",");
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                for (int i=0; i < split.length; i++) {
                    authorities.add(new GrantedAuthorityImpl(split[i]) {
                    });
                }
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
        } catch (ExpiredJwtException e) {
            logger.error("Token已过期: {} " + e);
            throw new TokenException("Token已过期");
        } catch (UnsupportedJwtException e) {
            logger.error("Token格式错误: {} " + e);
            throw new TokenException("Token格式错误");
        } catch (MalformedJwtException e) {
            logger.error("Token没有被正确构造: {} " + e);
            throw new TokenException("Token没有被正确构造");
        } catch (IllegalArgumentException e) {
            logger.error("非法参数异常: {} " + e);
            throw new TokenException("非法参数异常");
        }
        return null;
    }
}
