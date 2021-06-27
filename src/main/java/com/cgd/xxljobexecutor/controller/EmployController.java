package com.cgd.xxljobexecutor.controller;

import com.alibaba.fastjson.*;
import com.cgd.xxljobexecutor.model.*;
import com.cgd.xxljobexecutor.service.SiteListService;
import com.cgd.xxljobexecutor.service.WebSiteDetailService;
import com.cgd.xxljobexecutor.service.WebSiteService;
import com.cgd.xxljobexecutor.utils.AnalyzingXML;
import com.cgd.xxljobexecutor.utils.HttpRequestUtil;
import com.xxl.job.core.biz.model.ReturnT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/20 21:36
 */
@Controller
@Slf4j
@Api(tags = "网站收录")
@CrossOrigin(origins = "*", maxAge = 3600)
@RefreshScope
public class EmployController {

    @Autowired
    private WebSiteService webSiteService;

    @Autowired
    private WebSiteDetailService webSiteDetailService;

    @Autowired
    private SiteListService siteListService;

    @ApiOperation("新增需要收录的主站")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessages<String> get(@RequestParam(value = "siteMap") String siteMap, @RequestParam(value = "url") String url, @RequestParam(value = "token") String token) {
        try {
            int num = AnalyzingXML.AnalyzingXML(siteMap).size();
            WebSiteModel model = new WebSiteModel(null, siteMap, url, token, null, num);
            webSiteService.insert(model);
            return ResponseMessages.SUCCESS("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessages.FAIL(e.getMessage());
        }
    }

    @ApiOperation("测试获取百度站点")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public void test(@RequestParam(value = "param") String param) {
        String url = "https://openapi.baidu.com/rest/2.0/tongji/config/getSiteList";
        String resonse = HttpRequestUtil.sendGet(url, param);
        JSONObject message = JSONObject.parseObject(resonse);
        siteListService.saveSiteInfo(message);
    }
}
