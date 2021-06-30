package com.cgd.xxljobexecutor.dao;

import com.cgd.xxljobexecutor.model.SiteTrendOriginModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/30 20:42
 */
@Mapper
public interface SiteTrendOriginDao {
    void insert(List<SiteTrendOriginModel> list);
}
