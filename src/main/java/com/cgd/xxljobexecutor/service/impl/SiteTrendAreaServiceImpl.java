package com.cgd.xxljobexecutor.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.executor.SiteTrendAreaDao;
import com.cgd.xxljobexecutor.model.SiteTrendAreaModel;
import com.cgd.xxljobexecutor.service.SiteTrendAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/30 18:41
 */
@Service
public class SiteTrendAreaServiceImpl implements SiteTrendAreaService {

    @Autowired
    private SiteTrendAreaDao siteTrendAreaDao;

    @Override
    public void saveInfo(String response, String siteId) {
        List<SiteTrendAreaModel> list = new ArrayList<>();
        JSONArray areas = JSONObject.parseObject(response).getJSONObject("result").getJSONArray("items").getJSONArray(0);
        JSONArray jsonArray = JSONObject.parseObject(response).getJSONObject("result").getJSONArray("items").getJSONArray(1);
        for(int i = 0;i < areas.size();i++){
            list.add(new SiteTrendAreaModel(siteId,areas.getJSONArray(i).getJSONObject(0).getString("name"),jsonArray.getJSONArray(i).getInteger(0),jsonArray.getJSONArray(i).getInteger(1),jsonArray.getJSONArray(i).getInteger(2),jsonArray.getJSONArray(i).getInteger(3),jsonArray.getJSONArray(i).getInteger(4)));
        }
        siteTrendAreaDao.insert(list);
    }
}
