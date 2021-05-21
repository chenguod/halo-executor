package com.cgd.xxljobexecutor.dao;

import com.cgd.xxljobexecutor.model.WebHomeModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chengd
 * @version 1.0
 * @date 2021/5/21 12:32
 */

@Mapper
public interface WebHomeDao {

    int insert(WebHomeModel model);

    List<String> selectAll();
}
