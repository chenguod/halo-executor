package com.cgd.xxljobexecutor.controller;

import com.alibaba.fastjson.*;
import com.cgd.xxljobexecutor.model.*;
import com.cgd.xxljobexecutor.service.SiteListService;
import com.cgd.xxljobexecutor.service.WebSiteDetailService;
import com.cgd.xxljobexecutor.service.WebSiteService;
import com.cgd.xxljobexecutor.utils.AnalyzingXML;
import com.cgd.xxljobexecutor.utils.DateUtils;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    public void test(@RequestParam(value = "param") String param) throws ParseException {
        String date =DateUtils.nowDate(-1,"yyyy-MM-dd HH:mm:ss");
        String accessToken = "121.a56ea6e11fce00b3aa4c68b956d59504.YlPjhg44X-6WKT1J0s1e10rcpPuoE57OlTadyKY.q4_OvQ";//json.getString("access_token");
        String siteId = "16873420";//json.getString("site_id");
        String url = "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token="+accessToken+"&site_id="+siteId+"&start_date="+date+"&end_date="+date+"&metrics=pv_count%2Cpv_ratio%2Cvisit_count%2Cvisitor_count%2Cnew_visitor_count%2Cnew_visitor_ratio%2Cip_count%2Cbounce_ratio%2Cavg_visit_time%2Cavg_visit_pages&method=trend%2Ftime%2Fa&gran=day&area=china";
        String resonse = HttpRequestUtil.sendGet(url);
        JSONArray jsonArray = JSONObject.parseObject(resonse).getJSONObject("result").getJSONArray("sum").getJSONArray(0);
        System.out.println(jsonArray);
    }
}
