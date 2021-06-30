package com.cgd.xxljobexecutor.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.SiteTrendAreaDao;
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
    public void saveInfo(String response, String siteId, String date) {
        List<SiteTrendAreaModel> list = new ArrayList<>();
        JSONArray areas = JSONObject.parseObject(response).getJSONObject("result").getJSONArray("items").getJSONArray(0);
        JSONArray jsonArray = JSONObject.parseObject(response).getJSONObject("result").getJSONArray("items").getJSONArray(1);
        for(int i = 0;i < areas.size();i++){
            System.out.println(areas.getString(i).substring(2,areas.getString(i).length()-2));
            list.add(new SiteTrendAreaModel(siteId,areas.getString(i).substring(2,areas.getString(i).length()-2),jsonArray.getJSONArray(i).getInteger(0),jsonArray.getJSONArray(i).getBigDecimal(1),date));
        }
        siteTrendAreaDao.insert(list);
    }
}
