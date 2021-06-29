package com.cgd.xxljobexecutor.xxlJob;

import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.model.WebSiteDetailModel;
import com.cgd.xxljobexecutor.model.WebSiteModel;
import com.cgd.xxljobexecutor.model.XmlDTO;
import com.cgd.xxljobexecutor.service.WebSiteDetailService;
import com.cgd.xxljobexecutor.service.WebSiteService;
import com.cgd.xxljobexecutor.utils.AnalyzingXML;
import com.cgd.xxljobexecutor.utils.HttpRequestUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public ReturnT<String> pushJobHandler(String param) {
        WebSiteDetailModel model = new WebSiteDetailModel();
        List<WebSiteModel> webSiteModelList = webSiteService.selectAll();
        StringBuffer sb = new StringBuffer();
        webSiteModelList.stream().forEach(e -> {
            model.setPId(e.getId());
            model.setPushFlag(0);
            List<WebSiteDetailModel> webSiteDetailModelList = webSiteDetailService.selectAll(model);
            List list = webSiteDetailModelList.stream().map(n -> n.getUrl()).collect(Collectors.toList());
            if (list == null || list.size() == 0) {
                sb.append("<p style=\"color:yellow\">网站:" + e.getUrl() + "暂无需要推送的站点信息!!!</p>\n");
                return;
            }
            //拼装需要推送的url
            String pushUrl = "http://data.zz.baidu.com/urls?site=" + e.getUrl() + "&token=" + e.getToken();
            String response = HttpRequestUtil.baiduPost(pushUrl, list);
            JSONObject json = JSONObject.parseObject(response);
            if (json != null && json.get("success") != null) {
                webSiteDetailService.updatePushFlag(list);
                sb.append("<p style=\"color:green\">网站:" + e.getUrl() + "成功推送" + json.get("success") + "条数据</p>\n");
            } else {
                sb.append("<p style=\"color:red\">网站:" + e.getUrl() + "推送失败!!!</p>\n");
            }
        });
        String message = new String(sb);
        return new ReturnT<>(ReturnT.SUCCESS_CODE, message);
    }

    @XxlJob("UpdateUrl")
    public ReturnT<String> updateUrl(String param) throws Exception {
        List<WebSiteModel> webSiteModelList = webSiteService.selectAll();
        StringBuffer sb = new StringBuffer();
        for (WebSiteModel model : webSiteModelList) {
            Integer pId = model.getId();
            List<XmlDTO> xmlDTOList = AnalyzingXML.AnalyzingXML(model.getSiteMap());
            final int[] num = {0};
            xmlDTOList.stream()
                    .forEach(xml -> {
                        try {
                            //利用唯一索引的特性，如果不存在则插入，存在则快速失败
                            webSiteDetailService.insert(new WebSiteDetailModel(null, pId, xml.getLoc(), xml.getLastmod(), null));
                            num[0]++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
            XxlJobLogger.log("<p style=\"color:green\">>" + model.getUrl() + ":此次共更新" + num[0] + "条文章" + "</p>");
            sb.append("<p style=\"color:green\">" + model.getUrl() + ":此次共更新" + num[0] + "条文章" + "</p>");
            sb.append("\n");
        }
        String message = new String(sb);
        return new ReturnT<>(ReturnT.SUCCESS_CODE, message);
    }
}
