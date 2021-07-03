package com.cgd.xxljobexecutor.controller;

import com.cgd.xxljobexecutor.model.DTO.VisitDTO;
import com.cgd.xxljobexecutor.model.DTO.VisitVO;
import com.cgd.xxljobexecutor.service.BaiduCountService;
import com.cgd.xxljobexecutor.utils.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 18:40
 */
@Api(tags = "百度统计")
@Controller
@RequestMapping(value = "/baidu")
@CrossOrigin(origins = "*",maxAge = 3600)
public class BaiduCountController {

    @Autowired
    private BaiduCountService baiduCountService;

    @ApiOperation("获取百度统计趋势数据-月度")
    @RequestMapping(value = "/siteTrend",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseMessage<VisitVO> getSiteTrend(){
        VisitVO vo = baiduCountService.getVisitData();
        return new ResponseMessage(ResponseMessage.SUCCESS_CODE,"success",vo);
    }
}
