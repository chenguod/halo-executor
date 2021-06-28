package com.cgd.xxljobexecutor.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.SiteTrendDao;
import com.cgd.xxljobexecutor.model.SiteTrendModel;
import com.cgd.xxljobexecutor.service.SiteTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void saveInfo(String response,String siteId) {
        try {

            JSONArray jsonArray = JSONObject.parseObject(response).getJSONObject("result").getJSONArray("sum").getJSONArray(0);
            String s = String.valueOf(jsonArray);
            System.out.println(s);
            SiteTrendModel model = JSONArray.parseObject(String.valueOf(jsonArray),SiteTrendModel.class);
            System.out.println(model);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
