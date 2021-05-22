package com.cgd.xxljobexecutor.xxlJob;

import com.cgd.xxljobexecutor.model.WebSiteDetailModel;
import com.cgd.xxljobexecutor.model.WebSiteModel;
import com.cgd.xxljobexecutor.model.XmlDTO;
import com.cgd.xxljobexecutor.service.WebSiteService;
import com.cgd.xxljobexecutor.utils.AnalyzingXML;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @XxlJob("PushJobHandler")
    public ReturnT<String> PushJobHandler(String param){
        List<WebSiteModel> xmlList = webSiteService.selectAll();
        WebSiteDetailModel webSiteDetailModel = null;
/*        xmlList.stream().forEach(xml->{
            try {
                List<XmlDTO> list = AnalyzingXML.AnalyzingXML(xml.getUrl());
                webSiteDetailModel.setPId(xml.getId());
                webSiteDetailModel.setUrl(xml.getUrl());
                webSiteDetailModel.setCreateDate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
        return ReturnT.SUCCESS;
    }
}
