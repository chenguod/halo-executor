package com.cgd.xxljobexecutor.controller;

import com.cgd.xxljobexecutor.utils.HttpRequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/20 21:36
 */
@Controller
@Slf4j
@Api(tags = "用户信息管理")
public class TestController {

    @ApiOperation("新增数据")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public Document get(){
        String xml = HttpRequestUtil.sendGet("https://halo.chenmx.net/sitemap.xml","");
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element Response = doc.getRootElement();
            Iterator<?> Report = Response.elementIterator("url");
            while (Report.hasNext()) {
                Element tableItem = (Element) Report.next();
                String msgID = tableItem.elementTextTrim("MsgID");
                String url = tableItem.elementTextTrim("loc");
                String time = tableItem.elementTextTrim("lastmod");
                log.info("SmsReply>>>>>>>>>>url:" + url + ",time:" + time);

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
