package com.microservice.cloud.gateway.config;

import com.microservice.cloud.authen.security.deal.CustomAuthenticationProvider;
import com.microservice.cloud.gateway.filter.JWTAuthenticationFilter;
import com.microservice.cloud.gateway.handler.AuthenticationSuccessHandler;
import com.microservice.cloud.gateway.handler.CustomAuthenticationFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author junyunxiao
 * @version 1.0
 * @date 2018/11/28 15:44
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailServiceImpl;   ;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailHandler customAuthenticationFailHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
               http.addFilter(new JWTAuthenticationFilter(authenticationManager())).csrf().disable();;
    }
}
