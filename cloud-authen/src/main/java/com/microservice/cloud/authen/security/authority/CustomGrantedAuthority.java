package com.microservice.cloud.authen.security.authority;

import com.microservice.cloud.common.domain.menu.Menu;
import com.microservice.cloud.common.domain.permission.Permission;
import com.microservice.cloud.common.domain.role.Role;
import com.microservice.cloud.common.utils.JSONUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: 权限信息载体封装
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/11/29 13:44
 */
public class CustomGrantedAuthority implements GrantedAuthority {

    /**
     * 角色信息
     */
    private final List<Role> role;

    private final List<Permission> permission;

    private final List<Menu> menu;


    /**
     * 构造角色权限数据
     * @param role
     * @param permission
     */
    public CustomGrantedAuthority(List<Role> role, List<Menu> menu, List<Permission> permission) {
       /* Assert.hasText(role, "A granted authority textual representation is required");*/
        this.role = role;
        this.menu=menu;
        this.permission=permission;
    }

    @Override
    public String getAuthority() {
        Map<String,List> map=new HashMap<>();
        map.put("role",role);
        map.put("permission",permission);
        map.put("menu",menu);
        return JSONUtils.toJSON(map);
    }
}
