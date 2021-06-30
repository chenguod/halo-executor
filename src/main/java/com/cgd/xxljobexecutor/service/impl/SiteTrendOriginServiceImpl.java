package com.cgd.xxljobexecutor.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.SiteTrendOriginDao;
import com.cgd.xxljobexecutor.model.SiteTrendAreaModel;
import com.cgd.xxljobexecutor.model.SiteTrendOriginModel;
import com.cgd.xxljobexecutor.service.SiteTrendOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/30 20:43
 */
@Service
public class SiteTrendOriginServiceImpl implements SiteTrendOriginService {

    @Autowired
    private SiteTrendOriginDao siteTrendOriginDao;

    @Override
    public void saveInfo(String response, String siteId, String date) {
        List<SiteTrendOriginModel> list = new ArrayList<>();
        JSONArray jsonArray = JSONObject.parseObject(response).getJSONObject("result").getJSONObject("sourceSite").getJSONArray("items");
        System.out.println(jsonArray.getString(0).substring(1, jsonArray.getString(0).length() - 1));
        for (int i = 0; i < jsonArray.size(); i++) {
            String[] arrays = jsonArray.getString(i).substring(1, jsonArray.getString(i).length() - 1).replace("\"","").split(",");
            list.add(new SiteTrendOriginModel(siteId, arrays[0],Integer.valueOf(arrays[1]), new BigDecimal(arrays[2]), date));
        }
        siteTrendOriginDao.insert(list);
    }
}
