package com.microservice.cloud.user.service;

import com.microservice.cloud.common.domain.user.User;
import com.microservice.cloud.common.page.Page;
import com.microservice.cloud.data.service.DataServiceMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO: 类描述
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/11/15 9:19
 */
@Service(value = "userService")
public class UserService {

    @Autowired
    private DataServiceMybatis dataServiceStat;

    /**
     * 用户登录接口
     * @parameter：输入参数
     *      userName 用户名
     *
     * @return
     */
    public User queryUserByName(String userName) throws Exception{

        return dataServiceStat.getObject("com.microservice.cloud.user.dao.UserDao.queryUserByName",userName);
    }


    /**
     * 获取用户信息并分页
     * @param user
     * @param page
     * @return
     * @throws Exception
     */
    public Page<User> queryUserByPage(User user, Page page) throws Exception{

        return dataServiceStat.query("com.microservice.cloud.user.dao.UserDao.queryUserByPage","com.microservice.cloud.user.dao.UserDao.queryUserCount",user,page);
    }

}
