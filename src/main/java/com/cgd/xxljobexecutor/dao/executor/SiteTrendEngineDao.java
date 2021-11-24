package com.cgd.xxljobexecutor.dao.executor;

import com.cgd.xxljobexecutor.model.DTO.AreaDTO;
import com.cgd.xxljobexecutor.model.SiteTrendEngineModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/1 18:49
 */
@Mapper
public interface SiteTrendEngineDao {

    void insert(List<SiteTrendEngineModel> list);

    List<AreaDTO> selectAll();
}
