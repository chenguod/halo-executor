package com.cgd.xxljobexecutor.dao.executor;

import com.cgd.xxljobexecutor.model.DTO.AreaDTO;
import com.cgd.xxljobexecutor.model.SiteTrendSourceModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/3 20:39
 */
@Mapper
public interface SiteTrendSourceDao {
    void insert(List<SiteTrendSourceModel> list);

    List<AreaDTO> selectAll();
}
