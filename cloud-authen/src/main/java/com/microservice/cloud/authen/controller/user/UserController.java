package com.microservice.cloud.authen.controller.user;

import com.microservice.cloud.common.domain.user.User;
import com.microservice.cloud.common.message.TradeMessages;
import com.microservice.cloud.common.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * TODO: 用户服务控制层
 *
 * @author junyunxiao
 * @date 2018-9-10 10:06
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 尚未认证
     * @return
     * @throws Exception
     */
    @GetMapping("/unAuthorized")
    @ResponseBody
    public TradeMessages<String> oauthLogin()throws Exception{
        TradeMessages<String> messages=new TradeMessages<>();
        messages.setResultCode("401");
        messages.setResultMessage("尚未认证，请进行身份认证后再访问");
        messages.setData(null);
        return messages;
    }

    @RequestMapping("/toLoginPage")
    @ResponseBody
    public TradeMessages<String> toLoginPage() throws Exception{
        TradeMessages<String> messages=new TradeMessages<>();
        messages.setResultCode("100404");
        messages.setResultMessage("请求不存在，跳转至登录页");
        messages.setData(null);
        return messages;
    }

    @RequestMapping("/register")
    @ResponseBody
    public TradeMessages<String> userRegister(User user){
        user.setUserPwd(bCryptPasswordEncoder.encode(user.getUserPwd()));
        //return userService.userRegister(user);
        log.info("用户开始注册...");
        return null;
    }

    @GetMapping("/queryUserByPage")
    public Page<User> queryUserByPage(Map<String,Object> parameter) throws Exception{
        Page<User> userPage=new Page<>();
        userPage.setPageCurrent(1);
        userPage.setPageSize(30);
       /* Page<User> result=userService.queryUserByPage(parameter,userPage);
        return result;*/
       return null;
    }
}
