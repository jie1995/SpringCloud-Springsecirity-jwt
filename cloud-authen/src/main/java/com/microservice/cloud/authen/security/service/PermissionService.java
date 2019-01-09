package com.microservice.cloud.authen.security.service;

import com.microservice.cloud.authen.security.service.imp.PermissionServiceImpl;
import com.microservice.cloud.common.domain.menu.Menu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: 微笑天使
 * @Date: 2019/1/3 11:45
 * @Version 1.0
 */
@FeignClient(name = "cloud-user", fallback = PermissionServiceImpl.class)
@Service
public interface PermissionService{
      @RequestMapping("/queryUserByname")
      List<Menu> queryPermissionsByUserId(long userId);
}
