package com.cgd.xxljobexecutor.service.impl;

import com.cgd.xxljobexecutor.dao.WebSiteDao;
import com.cgd.xxljobexecutor.model.WebSiteModel;
import com.cgd.xxljobexecutor.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 12:30
 */
@Service
public class WebSiteServiceImpl implements WebSiteService {

    @Autowired
    private WebSiteDao webSiteDao;

    @Override
    public int insert(WebSiteModel model) {
        return webSiteDao.insert(model);
    }

    @Override
    public List<WebSiteModel> selectAll() {
        return webSiteDao.selectAll();
    }
}
