package com.cgd.xxljobexecutor.service;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/27 10:26
 */
public interface SiteListService {

    List<String> getSiteIds();

    Integer saveSiteInfo(String response);
}
