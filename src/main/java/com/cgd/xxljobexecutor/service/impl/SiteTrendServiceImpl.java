package com.cgd.xxljobexecutor.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.executor.SiteTrendDao;
import com.cgd.xxljobexecutor.model.SiteTrendModel;
import com.cgd.xxljobexecutor.service.SiteTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/28 19:24
 */
@Service
public class SiteTrendServiceImpl implements SiteTrendService {

    @Autowired
    private SiteTrendDao siteTrendDao;

    @Override
    public void saveInfo(String response, String siteId, String date, String param) {
        JSONArray jsonArray = JSONObject.parseObject(response).getJSONObject("result").getJSONArray("sum").getJSONArray(0);
        SiteTrendModel model = new SiteTrendModel(siteId, jsonArray.getIntValue(0), jsonArray.getIntValue(1), jsonArray.getInteger(2), jsonArray.getInteger(3), jsonArray.getInteger(4), date);
        if ("month".equals(param)) {
            siteTrendDao.insertMonth(model);
        } else if ("day".equals(param)) {
            siteTrendDao.insert(model);
        }
    }
}
