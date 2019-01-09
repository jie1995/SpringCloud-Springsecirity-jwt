package com.microservice.cloud.authen.security.service;

import com.microservice.cloud.authen.security.authority.CustomGrantedAuthority;
import com.microservice.cloud.common.domain.menu.Menu;
import com.microservice.cloud.common.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 类描述
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/11/27 10:24
 */
@Component("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService,SocialUserDetailsService {
    private Logger logger= LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserService userService;

 /*   @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuService menuService;*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户认证开始，待验证用户名:{}",username);
        //获取用户信息及角色
        User user =userService.queryUserByName(username);
        if (StringUtils.isEmpty(user)){
            throw new UsernameNotFoundException("用户："+username+"不存在，请检查");
        }
       //获取用户权限
       /* List<Permission> permissions =permissionService.queryPermissionsByUserId(user.getId());
        List<Menu> menus=menuService.queryMenusByUserId(user.getId());*/
        List<GrantedAuthority> authorities=new ArrayList<>();
       /* if (!StringUtils.isEmpty(user.getRoles())){
            authorities.add(new CustomGrantedAuthority(user.getRoles(),menus,permissions));
        }*/
        return  new org.springframework.security.core.userdetails.User(user.getUserName(),user.getUserPwd() ,true,true,true,true,authorities);

    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        return null;
    }
}
