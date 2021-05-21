package com.cgd.xxljobexecutor.dao;

import com.cgd.xxljobexecutor.model.WebSiteModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 12:32
 */

@Mapper
public interface WebSiteDao {

    int insert(WebSiteModel model);

    List<WebSiteModel> selectAll();
}
