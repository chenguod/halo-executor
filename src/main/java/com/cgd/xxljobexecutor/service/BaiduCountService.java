package com.cgd.xxljobexecutor.service;

import com.cgd.xxljobexecutor.model.VO.AreaVO;
import com.cgd.xxljobexecutor.model.VO.VisitVO;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 18:51
 */
public interface BaiduCountService {

    VisitVO getVisitData();

    AreaVO getAreaDta();
}
