package com.cgd.xxljobexecutor.service.impl;

import com.cgd.xxljobexecutor.dao.WebHomeDao;
import com.cgd.xxljobexecutor.model.WebHomeModel;
import com.cgd.xxljobexecutor.service.WebHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chengd
 * @version 1.0
 * @date 2021/5/21 12:30
 */
@Service
public class WebHomeServiceImpl implements WebHomeService {

    @Autowired
    private WebHomeDao webHomeDao;

    @Override
    public int insert(WebHomeModel model) {
        return webHomeDao.insert(model);
    }

    @Override
    public List<String> selectAll() {
        return webHomeDao.selectAll();
    }
}
