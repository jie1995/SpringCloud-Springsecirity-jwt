package com.microservice.cloud.authen.security.service;

import com.microservice.cloud.common.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 微笑天使
 * @Date: 2019/1/3 11:45
 * @Version 1.0
 */
//@FeignClient(name = "cloud-user", fallback = UserServiceImpl.class)
@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * temp
     *
     * @param userId
     * @return
     */
    //@RequestMapping("queryUserById")
    User queryUserById(long userId) {
        return null;
    }

    User queryUserByName(String userName) {
        User user = new User();
        user.setId(15173126889L);
        user.setUserName("weili");
        user.setUserPwd(bCryptPasswordEncoder.encode("chengzi19950922"));
        return user;
    }
}
