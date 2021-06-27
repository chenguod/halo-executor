package com.cgd.xxljobexecutor.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.SiteListDao;
import com.cgd.xxljobexecutor.model.SiteListModel;
import com.cgd.xxljobexecutor.service.SiteListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/27 10:27
 */
@Service
@Slf4j
public class SiteListServiceImpl implements SiteListService {

    @Autowired
    private SiteListDao siteListDao;

    @Override
    public SiteListModel getSiteInfo() {
        return null;
    }

    @Override
    public Integer saveSiteInfo(JSONObject message) {
        int num = 0;
        try {
            List<SiteListModel> list = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) message.get("list");
            for(int i = 0;i < jsonArray.size();i++){
                SiteListModel model = jsonArray.getJSONObject(i).toJavaObject(SiteListModel.class);
                list.add(model);
            }
            num = siteListDao.saveSiteInfo(list);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        finally {
            return num;
        }
    }
}
