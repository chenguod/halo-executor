package com.cgd.xxljobexecutor.service.impl;

import com.cgd.xxljobexecutor.dao.SiteTrendDao;
import com.cgd.xxljobexecutor.model.SiteTrendModel;
import com.cgd.xxljobexecutor.service.BaiduCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 18:51
 */
@Service
public class BaiduCountServiceImpl implements BaiduCountService {

    @Autowired
    private SiteTrendDao siteTrendDao;

    @Override
    public List<SiteTrendModel> getVisitData(SiteTrendModel model) {
        List<SiteTrendModel> siteTrendModelList = siteTrendDao.selectAll(model);
        return null;
    }
}
