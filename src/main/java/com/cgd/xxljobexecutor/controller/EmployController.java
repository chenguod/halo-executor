package com.cgd.xxljobexecutor.controller;

import com.cgd.xxljobexecutor.model.ResponseMessages;
import com.cgd.xxljobexecutor.model.WebSiteModel;
import com.cgd.xxljobexecutor.service.WebSiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("新增需要收录的主站")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessages<String> get(@RequestParam(value = "url") String url, @RequestParam(value = "token") String token){
        try {
            WebSiteModel model = new WebSiteModel();
            model.setUrl(url);
            model.setToken(token);
            webSiteService.insert(model);
            return ResponseMessages.SUCCESS("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessages.FAIL(e.getMessage());
        }
    }
}
