package com.microservice.cloud.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * TODO:HystrixDashboard监控微服务启动类
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/12 16:20
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableDiscoveryClient
public class CloudHystrixDashboardApplication {


    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixDashboardApplication.class, args);
    }
}
