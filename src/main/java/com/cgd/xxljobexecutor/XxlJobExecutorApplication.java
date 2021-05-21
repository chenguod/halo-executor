package com.cgd.xxljobexecutor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableDiscoveryClient
@EnableOpenApi
public class XxlJobExecutorApplication {
    public static void main(String[] args) {
        SpringApplication.run(XxlJobExecutorApplication.class, args);
    }
}
