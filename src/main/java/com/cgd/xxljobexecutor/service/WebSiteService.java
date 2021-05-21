package com.cgd.xxljobexecutor.service;

import com.cgd.xxljobexecutor.model.WebSiteModel;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 12:30
 */
public interface WebSiteService {

    int insert(WebSiteModel model);

    List<WebSiteModel> selectAll();
}
