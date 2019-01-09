package com.microservice.cloud.authen.security.service.imp;

import com.microservice.cloud.authen.security.service.PermissionService;
import com.microservice.cloud.common.domain.menu.Menu;

import java.util.List;

/**
 * @Author: 微笑天使
 * @Date: 2019/1/3 11:49
 * @Version 1.0
 */
public class PermissionServiceImpl implements PermissionService{
    @Override
    public List<Menu> queryPermissionsByUserId(long userId) {
        return null;
    }
}
