package com.cgd.xxljobexecutor.controller;

import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.executor.LinkRecordDao;
import com.cgd.xxljobexecutor.dao.executor.RefreshTokenDao;
import com.cgd.xxljobexecutor.dao.halo.LinksDao;
import com.cgd.xxljobexecutor.model.LinksModel;
import com.cgd.xxljobexecutor.model.VO.AreaVO;
import com.cgd.xxljobexecutor.model.VO.SourceVO;
import com.cgd.xxljobexecutor.model.VO.VisitVO;
import com.cgd.xxljobexecutor.service.*;
import com.cgd.xxljobexecutor.service.impl.SiteTrendServiceImpl;
import com.cgd.xxljobexecutor.utils.*;
import com.cgd.xxljobexecutor.xxlJob.BaiduStatistics;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 18:40
 */
@Api(tags = "百度统计")
@Controller
@RequestMapping(value = "/baidu")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class BaiduCountController {

    @Autowired
    private BaiduCountService baiduCountService;

    @Autowired
    private RefreshTokenDao refreshTokenDao;


    @Autowired
    private SiteTrendEngineService siteTrendEngineService;

    @ApiOperation("趋势分析-月度")
    @RequestMapping(value = "/siteTrend", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseMessage<VisitVO> getSiteTrend() {
        VisitVO vo = baiduCountService.getVisitData();
        return new ResponseMessage(ResponseMessage.SUCCESS_CODE, "success", vo);
    }

    @ApiOperation("地域分布")
    @RequestMapping(value = "/siteArea", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseMessage<AreaVO> getSiteArea() {
        AreaVO vo = baiduCountService.getAreaDta();
        return new ResponseMessage(ResponseMessage.SUCCESS_CODE, "success", vo);
    }

    @ApiOperation("来源分析")
    @RequestMapping(value = "/siteSource", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseMessage<SourceVO> getSiteSource() {
        SourceVO vo = baiduCountService.getSource();
        return new ResponseMessage(ResponseMessage.SUCCESS_CODE, "success", vo);
    }

    @ApiOperation("获取百度统计-访客数访问量")
    @RequestMapping(value = "/siteCount", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseMessage<Map> getSitePvCount() {
        Map map = baiduCountService.getPvCount();
        return new ResponseMessage<>(ResponseMessage.SUCCESS_CODE, "success", map);
    }

    public static void main(String[] args) {
        String site = "https://www.chenmx.net";
        String pat = "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";
        JSONObject gitHubUser = GitHubUtil.getGitHubUser(pat);

        String loginName = gitHubUser.getString("login");
        String repository = "halo-blog";
        String repositoryDesc = "✍  晓果冻的个人博客-一个热爱生活的90后";
        JSONObject body = new JSONObject();
        body.put("name", repository);
        body.put("description", repositoryDesc);
        body.put("homepage", site);
        body.put("has_wiki", false);
        body.put("has_projects", false);
        boolean ok = GitHubUtil.createOrUpdateGitHubRepo(pat, loginName, repository, "✍️ 晓果冻的个人博客 - 一个热爱生活的90后", site);
        System.out.println();


    }
}
