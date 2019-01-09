package com.microservice.cloud.authen.security.service.imp;

import org.springframework.stereotype.Component;

/**
 * @Author: 微笑天使
 * @Date: 2019/1/3 12:22
 * @Version 1.0
 */

@Component
public class RedisService {
    public String save() {
        return "123456";
    }

    public void hset(String a,String b,String c, Integer t) {
    }

    public String hget(String a,String b){
        return "123456";
    }

    public void hdel(String a,String b,String c){
    }
}
