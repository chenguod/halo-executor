package com.cgd.xxljobexecutor.xxlJob;

import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.service.SiteListService;
import com.cgd.xxljobexecutor.utils.HttpRequestUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/27 8:50
 */

@Component
public class BaiduStatistics {

    @Autowired
    private SiteListService siteListService;

    @XxlJob("GetSiteListHandler")
    public ReturnT<String> GetSiteList(String param){
        String url = "https://openapi.baidu.com/rest/2.0/tongji/config/getSiteList";
        String response = HttpRequestUtil.sendGet(url, param);
        int num = siteListService.saveSiteInfo(response);
        return new ReturnT<>(ReturnT.SUCCESS_CODE,"<p style=\"color:green\">获取:"+num+"条站点信息</p>\n");
    }
}
