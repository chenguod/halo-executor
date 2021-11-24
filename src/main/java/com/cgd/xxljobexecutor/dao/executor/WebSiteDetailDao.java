package com.cgd.xxljobexecutor.dao.executor;

import com.cgd.xxljobexecutor.model.WebSiteDetailModel;
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

    List<WebSiteDetailModel> selectAll(WebSiteDetailModel model);

    int insert(WebSiteDetailModel model);

    void truncate();

    void updatePushFlag(List list);
}
