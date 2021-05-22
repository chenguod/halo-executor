package com.cgd.xxljobexecutor.controller;

import com.cgd.xxljobexecutor.model.ResponseMessages;
import com.cgd.xxljobexecutor.model.WebSiteDetailModel;
import com.cgd.xxljobexecutor.model.WebSiteModel;
import com.cgd.xxljobexecutor.model.XmlDTO;
import com.cgd.xxljobexecutor.service.WebSiteDetailService;
import com.cgd.xxljobexecutor.service.WebSiteService;
import com.cgd.xxljobexecutor.utils.AnalyzingXML;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseMessages<String> get(@RequestParam(value = "url") String url, @RequestParam(value = "token") String token){
        try {
            int num = AnalyzingXML.AnalyzingXML(url).size();
            WebSiteModel model = new WebSiteModel(null,url,token,null,num);
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
        List<WebSiteModel> webSiteModelList = webSiteService.selectAll();
        StringBuffer sb = new StringBuffer();
        for (WebSiteModel model: webSiteModelList){
            Integer pId = model.getId();
            List<XmlDTO> xmlDTOList = AnalyzingXML.AnalyzingXML(model.getUrl());
            String name = model.getUrl();
            final int[] num = {0};
            xmlDTOList.stream()
                    .forEach(xml->{
                        try {
                            webSiteDetailService.insert(new WebSiteDetailModel(null,pId,xml.getLoc(),xml.getLastmod(),null));
                            num[0]++;
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    });
            System.out.println(num[0]);

        }
    }
}
