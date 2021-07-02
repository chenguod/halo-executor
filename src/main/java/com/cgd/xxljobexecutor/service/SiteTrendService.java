package com.cgd.xxljobexecutor.service;

import com.cgd.xxljobexecutor.model.SiteTrendModel;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/28 19:24
 */
public interface SiteTrendService {

    void saveInfo(String response, String siteId, String date);
    void saveMonthInfo(String response, String siteId, String date);

    List<SiteTrendModel> getSiteTrend();
}
