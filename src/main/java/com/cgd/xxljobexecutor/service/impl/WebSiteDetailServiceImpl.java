package com.cgd.xxljobexecutor.service.impl;

import com.cgd.xxljobexecutor.dao.WebSiteDetailDao;
import com.cgd.xxljobexecutor.model.WebSiteDetailModel;
import com.cgd.xxljobexecutor.model.XmlDTO;
import com.cgd.xxljobexecutor.service.WebSiteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 16:29
 */
@Service
public class WebSiteDetailServiceImpl implements WebSiteDetailService {

    @Autowired
    private WebSiteDetailDao webSiteDetailDao;

    @Override
    public int insertBatch(List<WebSiteDetailModel> list) {
        return webSiteDetailDao.insertBatch(list);
    }

    @Override
    public int insert(WebSiteDetailModel model) {
        return webSiteDetailDao.insert(model);
    }

    @Override
    public void truncate() {
        webSiteDetailDao.truncate();
    }

    @Override
    public List<WebSiteDetailModel> selectAll() {
        return webSiteDetailDao.selectAll();
    }
}
