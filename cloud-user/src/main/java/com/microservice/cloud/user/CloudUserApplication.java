package com.microservice.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO:spring cloud 用户微服务启动类
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/11 15:04
 */

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.microservice.cloud"})
public class CloudUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudUserApplication.class, args);
    }
}
