package com.cgd.xxljobexecutor.controller;

import com.cgd.xxljobexecutor.model.ResponseMessages;
import com.cgd.xxljobexecutor.model.WebHomeModel;
import com.cgd.xxljobexecutor.model.XmlDTO;
import com.cgd.xxljobexecutor.service.WebHomeService;
import com.cgd.xxljobexecutor.utils.AnalyzingXML;
import com.cgd.xxljobexecutor.utils.HttpRequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
    private WebHomeService webHomeService;

    @ApiOperation("新增需要收录的主站")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessages<String> get(@RequestParam(value = "url") String url, @RequestParam(value = "token") String token){
        try {
            WebHomeModel model = new WebHomeModel();
            model.setUrl(url);
            model.setToken(token);
            webHomeService.insert(model);
            return ResponseMessages.SUCCESS("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessages.FAIL(e.getMessage());
        }
    }
}
