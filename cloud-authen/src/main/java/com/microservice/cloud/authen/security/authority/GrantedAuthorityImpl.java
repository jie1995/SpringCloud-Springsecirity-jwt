package com.microservice.cloud.authen.security.authority;

import org.springframework.security.core.GrantedAuthority;

/**
 * @description  权限类型，负责存储权限和角色
 * @author 微笑天使
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}

