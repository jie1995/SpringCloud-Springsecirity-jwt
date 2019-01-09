package com.microservice.cloud.user.controller;

import com.microservice.cloud.common.domain.user.User;
import com.microservice.cloud.common.page.Page;
import com.microservice.cloud.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: 类描述
 *
 * @author junyunxiao
 * @date 2018-8-16 18:17
 */
@RestController
public class UserController {

    private Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/queryUserByname")
    public User queryUserByname(@RequestParam(value = "userName") String userName) throws Exception{
        return userService.queryUserByName(userName);
    }

    @PostMapping("/queryUserByPage")
    public Page<User> queryUserByPage(@RequestBody User user, @RequestBody Page page) throws Exception{
        return userService.queryUserByPage(user,page);
    }
}
