package com.microservice.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
/**
 * TODO:config 微服务启动类
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/12 16:20
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class CloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }

}

