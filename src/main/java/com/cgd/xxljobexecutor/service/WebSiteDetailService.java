package com.cgd.xxljobexecutor.service;

import com.cgd.xxljobexecutor.model.WebSiteDetailModel;
import com.cgd.xxljobexecutor.model.XmlDTO;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 16:29
 */
public interface WebSiteDetailService {

    int insertBatch(List<WebSiteDetailModel> list);

    int insert(WebSiteDetailModel model);

    void truncate();

    List<WebSiteDetailModel> selectAll();
}
