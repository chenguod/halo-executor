package com.cgd.xxljobexecutor.service;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/1 18:50
 */
public interface SiteTrendEngineService {
    void saveInfo(String engineResponse, String sourceResponse, String siteId, String date);
}
