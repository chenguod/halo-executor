package com.cgd.xxljobexecutor.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.SiteTrendSourceDao;
import com.cgd.xxljobexecutor.model.SiteTrendSourceModel;
import com.cgd.xxljobexecutor.service.SiteTrendSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/3 20:41
 */
@Service
public class SiteTrendSourceServiceImpl implements SiteTrendSourceService {

    @Autowired
    private SiteTrendSourceDao siteTrendSourceDao;

    @Override
    public void saveInfo(String response, String siteId, String date) {
        List<SiteTrendSourceModel> list = new ArrayList<>();
        JSONArray sources = JSONObject.parseObject(response).getJSONObject("result").getJSONArray("items").getJSONArray(0);
        JSONArray jsonArray = JSONObject.parseObject(response).getJSONObject("result").getJSONArray("items").getJSONArray(1);
        for (int i = 0; i < sources.size(); i++) {
            list.add(new SiteTrendSourceModel(siteId, sources.getJSONArray(i).getJSONObject(0).getString("name"),jsonArray.getJSONArray(i).getInteger(0), jsonArray.getJSONArray(i).getBigDecimal(1), jsonArray.getJSONArray(i).getInteger(2), jsonArray.getJSONArray(i).getInteger(3), jsonArray.getJSONArray(i).getInteger(4), jsonArray.getJSONArray(i).getBigDecimal(5), jsonArray.getJSONArray(i).getInteger(6), jsonArray.getJSONArray(i).getBigDecimal(7), jsonArray.getJSONArray(i).getInteger(8), jsonArray.getJSONArray(i).getInteger(9),date));
        }
        siteTrendSourceDao.insert(list);
    }
}
