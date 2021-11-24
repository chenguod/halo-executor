package com.cgd.xxljobexecutor.dao.executor;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/11/21 21:02
 */
@Mapper
public interface RefreshTokenDao {

    void insert(String refreshToken,String accessToken);

    Map<String,String> getRefreshToken();
}
