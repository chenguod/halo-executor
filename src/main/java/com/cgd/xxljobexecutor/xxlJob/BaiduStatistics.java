package com.cgd.xxljobexecutor.xxlJob;

import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.executor.RefreshTokenDao;
import com.cgd.xxljobexecutor.service.*;
import com.cgd.xxljobexecutor.service.impl.SiteTrendServiceImpl;
import com.cgd.xxljobexecutor.utils.DateUtils;
import com.cgd.xxljobexecutor.utils.HttpRequestUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/27 8:50
 */

@Component
public class BaiduStatistics {

    @Autowired
    private SiteListService siteListService;

    @Autowired
    private SiteTrendServiceImpl siteTrendService;

    @Autowired
    private SiteTrendAreaService siteTrendAreaService;

    @Autowired
    private SiteTrendOriginService siteTrendOriginService;

    @Autowired
    private SiteTrendEngineService siteTrendEngineService;

    @Autowired
    private SiteTrendSourceService siteTrendSourceService;

    //@Value("${accessToken}")
    public static String accessToken;

    @Autowired
    private RefreshTokenDao refreshTokenDao;

    /**
     * 定时获取百度统计网站列表
     *
     * @param param
     * @return
     */
    @XxlJob("GetSiteListHandler")
    public ReturnT<String> getSiteList(String param) {
        param = "access_token=" + accessToken;
        String url = "https://openapi.baidu.com/rest/2.0/tongji/config/getSiteList";
        String response = HttpRequestUtil.sendGet(url, param);
        int num = siteListService.saveSiteInfo(response);
        return new ReturnT<>(ReturnT.SUCCESS_CODE, "<p style=\"color:green\">获取:" + num + "条站点信息</p>\n");
    }

    /**
     * 趋势分析
     * 时间粒度-日、月
     *
     * @param param day:日颗粒度 month：月颗粒度
     * @return
     */
    @XxlJob("GetSiteTrendHandler")
    public ReturnT<String> getSiteTrend(String param) {
        String endDate = DateUtils.nowTime("yyyyMMdd");
        String startDate = endDate;
        if ("month".equals(param)) {
            startDate = DateUtils.getMonthFirst("yyyyMMdd");
        }
        List<String> siteIdList = siteListService.getSiteIds();
        try {
            for (String e : siteIdList) {
                String url = "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token=" + accessToken + "&site_id=" + e + "&start_date=" + startDate + "&end_date=" + endDate + "&metrics=pv_count%2Cvisit_count%2Cvisitor_count%2Cnew_visitor_count%2Cip_count&method=trend%2Ftime%2Fa&gran=" + param + "&area=china";
                String response = HttpRequestUtil.sendGet(url).replace("--", "0");
                if ("month".equals(param)) {
                    startDate = startDate.substring(0, 6);
                }
                siteTrendService.saveInfo(response, e, startDate, param);
            }
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }

    /**
     * 地域分布
     * 开始时间：20210101，结束时间：当月最后一天
     *
     * @param param 预留参数 后续可能会用到
     * @return
     */
    @XxlJob("GetSiteTrendAreaHandler")
    public ReturnT<String> getSiteTrendArea(String param) {
        String endDate = DateUtils.getMonthLast("yyyyMMdd");
        List<String> siteIdList = siteListService.getSiteIds();
        try {
            siteIdList.stream().forEach(e -> {
                String url = "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token=" + accessToken + "&site_id=" + e + "&start_date=20210101&end_date=" + endDate + "&metrics=pv_count%2Cvisit_count%2Cvisitor_count%2Cnew_visitor_count%2Cip_count&method=visit%2Fdistrict%2Fa";
                String response = HttpRequestUtil.sendGet(url);
                siteTrendAreaService.saveInfo(response, e);
            });
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }

    /**
     * 全部来源
     * 统计20210101-当前最后一天的数据
     *
     * @param param 预留参数 后续可能会用到
     * @return
     */
    @XxlJob("GetSiteSourceHandle")
    public ReturnT<String> getSiteSource(String param) {
        String endDate = DateUtils.getMonthLast("yyyyMMdd");
        List<String> siteIdList = siteListService.getSiteIds();
        try {
            siteIdList.stream().forEach(e -> {
                String url = "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token=" + accessToken + "&site_id=" + e + "&start_date=20210101&end_date=" + endDate + "&metrics=pv_count%2Cvisit_count%2Cvisitor_count%2Cnew_visitor_count%2Cip_count&method=source%2Fall%2Fa";
                String response = HttpRequestUtil.sendGet(url).replace("--", "0");
                siteTrendSourceService.saveInfo(response, e);
            });
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }

    /**
     * 搜索引擎
     * 统计自20210101-当前日期的数据
     *
     * @param param 预留参数 后续可能会用到
     * @return
     */
    @XxlJob("GetSiteSearchEngine")
    public ReturnT<String> getSiteSearchEngine(String param) {
        String date = DateUtils.getMonthLast("yyyyMMdd");
        List<String> siteIdList = siteListService.getSiteIds();
        try {
            siteIdList.stream().forEach(e -> {
                String url = "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token=" + accessToken + "&site_id=" + e + "&start_date=" + "20210101" + "&end_date=" + date + "&metrics=pv_count%2Cvisit_count%2Cvisitor_count%2Cnew_visitor_count%2Cip_count&method=source%2Fengine%2Fa&area=";
                String response = HttpRequestUtil.sendGet(url).replace("--", "0");
                siteTrendEngineService.saveInfo(response, e);
            });
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }

    /**
     * 自动获取accessToken和refreshToken
     * @param param
     * @return
     */
    @XxlJob("GetRefreshToken")
    public ReturnT<String> getRefreshToken(String param){
        Map<String,String> map = refreshTokenDao.getRefreshToken();
        String refreshToken = map.get("refreshToken");
        String accessToken = map.get("accessToken");
        String url = "http://openapi.baidu.com/oauth/2.0/token?grant_type=refresh_token&refresh_token=" + refreshToken + "&client_id=vZ3LfT4woCaZ0vsBDXZKSVBI&client_secret=jV3wzVuGbdtDoqt66TIAzTmVfUpttxBl";
        String response = HttpRequestUtil.sendGet(url);
        JSONObject json = JSONObject.parseObject(response);
        try {
            refreshToken = json.getString("refresh_token");
            refreshTokenDao.insert(refreshToken,accessToken);
            return new ReturnT<>(ReturnT.SUCCESS_CODE, "<p style=\"color:green\">获取refreshToken成功，信息如下：" + response + "</p>\n");
        }catch (Exception e){
            XxlJobLogger.log(e.getMessage());
            return new ReturnT<>(ReturnT.SUCCESS_CODE, "<p style=\"color:red\">获取refreshToken失败，信息如下：" + e.getMessage() + "</p>\n");
        }
    }

    /**
     * 抽取网站具体来源  具体到某个网址
     *
     * @param param 预留参数 后续可能会用到
     * @return
     */
    @XxlJob("GetCommonTrackRptHandler")
    public ReturnT<String> getCommonTrackRpt(String param) {
        String date = DateUtils.nowDate(-1, "yyyyMMdd");
        List<String> siteIdList = siteListService.getSiteIds();
        try {
            siteIdList.stream().forEach(e -> {
                String url = "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token=" + accessToken + "&site_id=" + e + "&start_date=" + date + "&end_date=" + date + "&metrics=pv_count&method=overview%2FgetCommonTrackRpt";
                String response = HttpRequestUtil.sendGet(url);
                siteTrendOriginService.saveInfo(response, e, date);
            });
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }

}
