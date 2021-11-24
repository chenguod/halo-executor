package com.cgd.xxljobexecutor.dao.halo;

import com.cgd.xxljobexecutor.model.LinksModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/10/24 14:14
 */
@Mapper
public interface LinksDao {
    List<LinksModel> getAllLinks();

    void delLinksById(Integer id);
}
