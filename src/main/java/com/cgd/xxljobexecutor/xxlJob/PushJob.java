package com.cgd.xxljobexecutor.xxlJob;

import com.cgd.xxljobexecutor.model.WebSiteDetailModel;
import com.cgd.xxljobexecutor.model.WebSiteModel;
import com.cgd.xxljobexecutor.model.XmlDTO;
import com.cgd.xxljobexecutor.service.WebSiteDetailService;
import com.cgd.xxljobexecutor.service.WebSiteService;
import com.cgd.xxljobexecutor.utils.AnalyzingXML;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/19 15:24
 */
@Component
public class PushJob {

    @Autowired
    private WebSiteService webSiteService;

    @Autowired
    private WebSiteDetailService webSiteDetailService;

    @XxlJob("PushJobHandler")
    public ReturnT<String> PushJobHandler(String param){
        List<WebSiteModel> xmlList = webSiteService.selectAll();
        WebSiteDetailModel webSiteDetailModel = null;
        return ReturnT.SUCCESS;
    }

    @XxlJob("UpdateUrl")
    public ReturnT<String> UpdateUrl(String param) throws Exception{
        List<WebSiteModel> webSiteModelList = webSiteService.selectAll();
        StringBuffer sb = new StringBuffer();
        for (WebSiteModel model: webSiteModelList){
            Integer pId = model.getId();
            List<XmlDTO> xmlDTOList = AnalyzingXML.AnalyzingXML(model.getUrl());
            final int[] num = {0};
            xmlDTOList.stream()
                    .forEach(xml->{
                        try {
                            //利用唯一索引的特性，如果不存在则插入，存在则快速失败
                            webSiteDetailService.insert(new WebSiteDetailModel(null,pId,xml.getLoc(),xml.getLastmod(),null));
                            num[0]++;
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    });
            XxlJobLogger.log("<p style=\"color:green\">>"+model.getUrl()+":此次共更新"+num[0]+"条文章"+"</p>");
            sb.append("<p style=\"color:green\">>"+model.getUrl()+":此次共更新"+num[0]+"条文章"+"</p>");
            sb.append("\n");
        }
        String message = new String(sb);
        return new ReturnT<>(message);
    }
}
