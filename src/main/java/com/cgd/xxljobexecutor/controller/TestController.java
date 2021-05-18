package com.cgd.xxljobexecutor.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chengd
 * @version 1.0
 * @date 2021/5/18 23:16
 */
@RestController
public class TestController {

    @NacosInjected
    private NamingService namingService;

    @GetMapping(value = "/list")
    public List<Instance> hell() throws NacosException {
        return namingService.getAllInstances("xxl_job");
    }
}
