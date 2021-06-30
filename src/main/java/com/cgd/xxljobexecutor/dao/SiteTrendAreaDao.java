package com.cgd.xxljobexecutor.dao;

import com.cgd.xxljobexecutor.model.SiteTrendAreaModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/30 18:38
 */
@Mapper
public interface SiteTrendAreaDao {

    void insert(List<SiteTrendAreaModel> list);
}
