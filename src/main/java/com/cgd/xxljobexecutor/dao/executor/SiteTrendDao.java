package com.cgd.xxljobexecutor.dao.executor;

import com.cgd.xxljobexecutor.model.DTO.VisitDTO;
import com.cgd.xxljobexecutor.model.SiteTrendModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/28 19:55
 */
@Mapper
public interface SiteTrendDao {

    void insert(SiteTrendModel model);

    void insertMonth(SiteTrendModel model);

    List<VisitDTO> selectVisitInfo();

    Map selectSitePvCount();
}
