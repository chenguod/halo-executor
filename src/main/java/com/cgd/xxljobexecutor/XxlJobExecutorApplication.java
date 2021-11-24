package com.cgd.xxljobexecutor;

import com.cgd.xxljobexecutor.dao.executor.RefreshTokenDao;
import com.cgd.xxljobexecutor.xxlJob.BaiduStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

import javax.annotation.PostConstruct;
import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
@EnableOpenApi
@Slf4j
public class XxlJobExecutorApplication {

    @Autowired
    private RefreshTokenDao refreshTokenDao;

    public static void main(String[] args) {
        SpringApplication.run(XxlJobExecutorApplication.class, args);
    }

    @PostConstruct
    public void initRefreshToken(){
        Map<String,String> map = refreshTokenDao.getRefreshToken();
        BaiduStatistics.accessToken = map.get("accessToken");
        log.info("开始初始化accessToken,信息如下:"+BaiduStatistics.accessToken);
    }
}
