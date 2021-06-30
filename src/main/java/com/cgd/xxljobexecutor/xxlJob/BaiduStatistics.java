package com.cgd.xxljobexecutor.xxlJob;

import com.cgd.xxljobexecutor.service.SiteListService;
import com.cgd.xxljobexecutor.service.SiteTrendAreaService;
import com.cgd.xxljobexecutor.service.impl.SiteTrendServiceImpl;
import com.cgd.xxljobexecutor.utils.DateUtils;
import com.cgd.xxljobexecutor.utils.HttpRequestUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/27 8:50
 */

@RefreshScope
@Component
public class BaiduStatistics {

    @Autowired
    private SiteListService siteListService;

    @Autowired
    private SiteTrendServiceImpl siteTrendService;

    @Autowired
    private SiteTrendAreaService siteTrendAreaService;

    @Value("${accessToken}")
    private String accessToken;

    @XxlJob("GetSiteListHandler")
    public ReturnT<String> getSiteList(String param) {
        param = "access_token=" + accessToken;
        String url = "https://openapi.baidu.com/rest/2.0/tongji/config/getSiteList";
        String response = HttpRequestUtil.sendGet(url, param);
        int num = siteListService.saveSiteInfo(response);
        return new ReturnT<>(ReturnT.SUCCESS_CODE, "<p style=\"color:green\">获取:" + num + "条站点信息</p>\n");
    }

    @XxlJob("GetSiteTrendHandler")
    public ReturnT<String> getSiteTrend(String param) {
        String date = DateUtils.nowDate(-1, "yyyyMMdd");
        List<String> siteIdList = siteListService.getSiteIds();
        try {
            siteIdList.stream().forEach(e -> {
                String url = "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token=" + accessToken + "&site_id=" + e + "&start_date=" + date + "&end_date=" + date + "&metrics=pv_count%2Cpv_ratio%2Cvisit_count%2Cvisitor_count%2Cnew_visitor_count%2Cnew_visitor_ratio%2Cip_count%2Cbounce_ratio%2Cavg_visit_time%2Cavg_visit_pages&method=trend%2Ftime%2Fa&gran=day&area=china";
                String response = HttpRequestUtil.sendGet(url).replace("--", "0");
                siteTrendService.saveInfo(response, e, date);
            });
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            return ReturnT.FAIL;
        }
    }

    @XxlJob("GetSiteTrendAreaHandler")
    public ReturnT<String> getSiteTrendArea(String param) {
        String date = DateUtils.nowDate(-1, "yyyyMMdd");
        List<String> siteIdList = siteListService.getSiteIds();
        try {
            siteIdList.stream().forEach(e -> {
                String url = "https://openapi.baidu.com/rest/2.0/tongji/report/getData?access_token=" + accessToken + "&site_id=" + e + "&start_date=" + date + "&end_date=" + date + "&metrics=pv_count&method=overview%2FgetDistrictRpt";
                String response = HttpRequestUtil.sendGet(url).replace("--", "0");
                siteTrendAreaService.saveInfo(response, e, date);
            });
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            return ReturnT.FAIL;
        }
    }
}
