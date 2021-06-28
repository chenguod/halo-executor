package com.cgd.xxljobexecutor.service;

import com.cgd.xxljobexecutor.model.SiteListModel;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/27 10:26
 */
public interface SiteListService {

    SiteListModel getSiteInfo();

    Integer saveSiteInfo(String response);
}
