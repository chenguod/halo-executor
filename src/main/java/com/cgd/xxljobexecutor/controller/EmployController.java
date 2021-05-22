package com.cgd.xxljobexecutor.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPathException;
import com.cgd.xxljobexecutor.model.ResponseMessages;
import com.cgd.xxljobexecutor.model.WebSiteDetailModel;
import com.cgd.xxljobexecutor.model.WebSiteModel;
import com.cgd.xxljobexecutor.model.XmlDTO;
import com.cgd.xxljobexecutor.service.WebSiteDetailService;
import com.cgd.xxljobexecutor.service.WebSiteService;
import com.cgd.xxljobexecutor.utils.AnalyzingXML;
import com.cgd.xxljobexecutor.utils.HttpRequestUtil;
import com.xxl.job.core.biz.model.ReturnT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/20 21:36
 */
@Controller
@Slf4j
@Api(tags = "网站收录")
@CrossOrigin(origins = "*",maxAge = 3600)
public class EmployController {

    @Autowired
    private WebSiteService webSiteService;

    @Autowired
    private WebSiteDetailService webSiteDetailService;

    @ApiOperation("新增需要收录的主站")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessages<String> get(@RequestParam(value = "siteMap") String siteMap,@RequestParam(value = "url") String url, @RequestParam(value = "token") String token){
        try {
            int num = AnalyzingXML.AnalyzingXML(url).size();
            WebSiteModel model = new WebSiteModel(null,siteMap,url,token,null,num);
            webSiteService.insert(model);
            return ResponseMessages.SUCCESS("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessages.FAIL(e.getMessage());
        }
    }

    @ApiOperation("56+48456456456")
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public void ggg() throws Exception{
        WebSiteDetailModel model = new WebSiteDetailModel();
        List<WebSiteModel> webSiteModelList = webSiteService.selectAll();
        StringBuffer sb = new StringBuffer();
        webSiteModelList.stream().forEach(e->{
            model.setPId(e.getId());
            model.setPushFlag(0);
            List<WebSiteDetailModel> webSiteDetailModelList = webSiteDetailService.selectAll(model);
            List list = webSiteDetailModelList.stream().map(n->n.getUrl()).collect(Collectors.toList());
            if(list==null||list.size() == 0){
                sb.append("<p style=\"color:yellow\">网站:"+e.getUrl()+"无需要推送的站点信息!!!</p>\n");
                return;
            }
            //拼装需要推送的url
            String pushUrl = "http://data.zz.baidu.com/urls?site="+e.getUrl()+"&token="+e.getToken();
            String response = HttpRequestUtil.baiduPost(pushUrl,list);
            JSONObject json = JSONObject.parseObject(response);
            if(json != null&& json.get("success") != null){
                webSiteDetailService.updatePushFlag(list);
                sb.append("<p style=\"color:green\">网站:"+e.getUrl()+"成功推送"+json.get("success")+"条数据\n</p>\n");
            }else{
                sb.append("<p style=\"color:red\">网站:"+e.getUrl()+"推送失败!!!</p>\n");
            }
        });
        String message = new String(sb);
        System.out.println(message);
    }
}
