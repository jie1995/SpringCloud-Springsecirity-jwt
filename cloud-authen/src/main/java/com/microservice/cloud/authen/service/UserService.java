package com.microservice.cloud.authen.service;

import com.microservice.cloud.authen.service.fallback.UserServiceFallback;
import com.microservice.cloud.common.domain.user.User;
import com.microservice.cloud.common.page.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO:
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/12 10:19
 */
@FeignClient(value = "cloud-user",fallback = UserServiceFallback.class)
public interface UserService {

    /**
     * 远程调用用户微服务
     * @param userName
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/queryUserByname")
    User queryUserFegins(@RequestParam("userName") String userName);

    /**
     * 远程调用用户微服务获取用户信息并分页
     * @param user
     * @param page
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/queryUserByPage")
    Page<User> queryUserByPageFegins(User user, Page page);
}
