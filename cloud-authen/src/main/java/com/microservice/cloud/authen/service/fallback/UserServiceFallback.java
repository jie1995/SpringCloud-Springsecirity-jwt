package com.microservice.cloud.authen.service.fallback;

import com.microservice.cloud.authen.service.UserService;
import com.microservice.cloud.common.domain.user.User;
import com.microservice.cloud.common.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * TODO:
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/12 10:37
 */
@Component
public class UserServiceFallback implements UserService {

     private Logger logger= LoggerFactory.getLogger(UserServiceFallback.class);

    @Override
    public User queryUserFegins(String userName) {
        logger.info("远程微服务出现异常，发生服务降级.........");
        return null;
    }

    @Override
    public Page<User> queryUserByPageFegins(User user, Page page) {
        logger.info("远程微服务出现异常，发生服务降级.........");
        return null;
    }
}
