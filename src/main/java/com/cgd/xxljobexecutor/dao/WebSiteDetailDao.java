package com.cgd.xxljobexecutor.dao;

import com.cgd.xxljobexecutor.model.WebSiteDetailModel;
import com.cgd.xxljobexecutor.model.XmlDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 16:33
 */
@Mapper
public interface WebSiteDetailDao {

    int insertBatch(List<WebSiteDetailModel> list);

    List<WebSiteDetailModel> selectAll();

    void truncate();
}
