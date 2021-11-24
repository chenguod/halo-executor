package com.cgd.xxljobexecutor.dao.executor;

import com.cgd.xxljobexecutor.model.LinksModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/10/24 17:42
 */
@Mapper
public interface LinkRecordDao {
    void insert(LinksModel model);
    void update(LinksModel model);
    List<LinksModel> getAllLinks();
    void deleteLinkById(Integer id);
}
