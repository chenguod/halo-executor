package com.cgd.xxljobexecutor.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.SiteTrendEngineDao;
import com.cgd.xxljobexecutor.dao.SiteTrendOriginDao;
import com.cgd.xxljobexecutor.model.SiteTrendEngineModel;
import com.cgd.xxljobexecutor.service.SiteTrendEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/1 18:50
 */
@Service
public class SiteTrendEngineServiceImpl implements SiteTrendEngineService {
    @Autowired
    private SiteTrendEngineDao siteTrendEngineDao;

    @Override
    public void saveInfo(String response, String siteId, String date) {
        List<SiteTrendEngineModel> list = new ArrayList<>();
        JSONArray searchEngines = JSONObject.parseObject(response).getJSONObject("result").getJSONArray("items").getJSONArray(0);
        JSONArray jsonArray = JSONObject.parseObject(response).getJSONObject("result").getJSONArray("items").getJSONArray(1);
        for (int i = 0; i < searchEngines.size(); i++) {
            list.add(new SiteTrendEngineModel(siteId,searchEngines.getJSONArray(i).getJSONObject(0).getString("name"),jsonArray.getJSONArray(i).getInteger(0),jsonArray.getJSONArray(i).getBigDecimal(1),jsonArray.getJSONArray(i).getInteger(2),jsonArray.getJSONArray(i).getInteger(3),jsonArray.getJSONArray(i).getInteger(4),jsonArray.getJSONArray(i).getBigDecimal(5),jsonArray.getJSONArray(i).getInteger(6),jsonArray.getJSONArray(i).getBigDecimal(7),jsonArray.getJSONArray(i).getInteger(8),jsonArray.getJSONArray(i).getInteger(9),date));
        }
        siteTrendEngineDao.insert(list);
    }
}
