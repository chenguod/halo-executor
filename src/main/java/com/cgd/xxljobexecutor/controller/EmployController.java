package com.cgd.xxljobexecutor.controller;

import com.cgd.xxljobexecutor.dao.SiteTrendAreaDao;
import com.cgd.xxljobexecutor.model.ResponseMessages;
import com.cgd.xxljobexecutor.model.WebSiteModel;
import com.cgd.xxljobexecutor.service.*;
import com.cgd.xxljobexecutor.utils.AnalyzingXML;
import com.cgd.xxljobexecutor.utils.DateUtils;
import com.cgd.xxljobexecutor.utils.HttpRequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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

    @Autowired
    private SiteTrendService siteTrendService;

    @Value("${accessToken}")
    private String accessToken;

    @Autowired
    private SiteTrendAreaService siteTrendAreaService;

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

    @ApiOperation("测试获取百度统计站点趋势数据")
    @RequestMapping(value = "/test/siteTrend", method = RequestMethod.POST)
    @ResponseBody
    public void getSiteTrend(@RequestParam(value = "param") String param) throws ParseException {
        String date = param;
        List<String> siteIdList = siteListService.getSiteIds();
        siteIdList.stream().forEach(e -> {
            String url = "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token=" + accessToken + "&site_id=" + e + "&start_date=" + date + "&end_date=" + date + "&metrics=pv_count%2Cpv_ratio%2Cvisit_count%2Cvisitor_count%2Cnew_visitor_count%2Cnew_visitor_ratio%2Cip_count%2Cbounce_ratio%2Cavg_visit_time%2Cavg_visit_pages&method=trend%2Ftime%2Fa&gran=day&area=china";
            String response = HttpRequestUtil.sendGet(url).replace("--", "0");
            siteTrendService.saveInfo(response, e, date);
        });
    }

    @ApiOperation("测试获取百度统计站点")
    @RequestMapping(value = "/test/siteInfo", method = RequestMethod.POST)
    @ResponseBody
    public void getSiteInfo(@RequestParam(value = "param") String param) throws ParseException {
        param = "access_token=" + accessToken;
        String url = "https://openapi.baidu.com/rest/2.0/tongji/config/getSiteList";
        String response = HttpRequestUtil.sendGet(url, param);
        int num = siteListService.saveSiteInfo(response);
    }

    @ApiOperation("测试获取百度统计站点趋势数据-地域")
    @RequestMapping(value = "/test/siteTrend/area", method = RequestMethod.POST)
    @ResponseBody
    public void getSiteTrendArea(@RequestParam(value = "param") String param) throws ParseException {
        String date = DateUtils.nowDate(-1, "yyyyMMdd");
        List<String> siteIdList = siteListService.getSiteIds();
        siteIdList.stream().forEach(e -> {
            String url = "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token=" + accessToken + "&site_id=" + e + "&start_date=" + date + "&end_date=" + date + "&metrics=pv_count&method=overview%2FgetDistrictRpt";
            String response = HttpRequestUtil.sendGet(url);
            siteTrendAreaService.saveInfo(response, e, date);
        });
    }
}
