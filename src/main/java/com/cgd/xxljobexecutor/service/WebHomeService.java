package com.cgd.xxljobexecutor.service;

import com.cgd.xxljobexecutor.dao.WebHomeDao;
import com.cgd.xxljobexecutor.model.WebHomeModel;
import com.cgd.xxljobexecutor.model.XmlDTO;

import java.util.List;

/**
 * @author chengd
 * @version 1.0
 * @date 2021/5/21 12:30
 */
public interface WebHomeService {

    int insert(WebHomeModel model);

    List<String> selectAll();
}
