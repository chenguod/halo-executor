package com.cgd.xxljobexecutor.dao.halo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/11/25 20:47
 */
@Mapper
public interface PostsDao {

    List<String> getPosts();
}
