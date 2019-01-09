package com.microservice.cloud.authen.security.service;

import com.microservice.cloud.authen.security.service.imp.MenuServiceImpl;
import com.microservice.cloud.common.domain.menu.Menu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @Author: 微笑天使
 * @Date: 2019/1/3 11:44
 * @Version 1.0
 */
@FeignClient(name = "cloud-user", fallback = MenuServiceImpl.class)
@Service
public interface MenuService {
    /**
     * 通过角色名查询菜单
     * @param role 角色名称
     * @return 菜单列表
     */
    @GetMapping(value = "/menu/findMenuByRole/{role}")
    Set<Menu> findMenuByRole(@PathVariable("role") String role);
}
