package com.cgd.xxljobexecutor.dao.executor;

import com.cgd.xxljobexecutor.model.SiteListModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/27 10:30
 */
@Mapper
public interface SiteListDao {

    int saveSiteInfo(List<SiteListModel> list);

    List<String> getSiteIds();
}
