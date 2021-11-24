package com.cgd.xxljobexecutor.xxlJob;

import com.alibaba.fastjson.JSONObject;
import com.cgd.xxljobexecutor.dao.executor.LinkRecordDao;
import com.cgd.xxljobexecutor.dao.halo.LinksDao;
import com.cgd.xxljobexecutor.model.LinksModel;
import com.cgd.xxljobexecutor.utils.CheckLinks;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/10/24 13:38
 */
@RefreshScope
@Component
@Slf4j
public class Links {

    @Autowired
    private LinksDao linksDao;

    @Autowired
    private LinkRecordDao linkRecordDao;

    @XxlJob("CheckWebHandler")
    public ReturnT<String> checkWeb(String url) {
        try {
            String info = CheckLinks.UrlWithTime(url, 1000);
            return new ReturnT<String>(ReturnT.SUCCESS_CODE, "<p style=\"color:green\">" + info + "</p>\n");
        } catch (Exception e1) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "<p style=\"color:red\">网站:" + url + "访问异常</p>\n");
        }
    }

    @XxlJob("CheckLinksHandler")
    public ReturnT<String> checkLinks(String param) {
        List<LinksModel> list = linkRecordDao.getAllLinks();
        StringBuilder sb = new StringBuilder();
        list.stream().forEach(e -> {
            try {
                //检查链接是否正确,暂时不需要验证，因为都是从halo数据库导入的正确数据
                //CheckLinks.isValidUrl(e.getUrl());
                CheckLinks.UrlWithTime(e.getUrl(), 1000);
                e.setIsNormal(1);
            } catch (Exception e1) {
                e.setIsNormal(0);
                e.setTotal(e.getTotal() + 1);
                sb.append("<p style=\"color:red\">网站:" + e.getUrl() + "访问异常</p>\n");
            }
            linkRecordDao.update(e);
        });
        return new ReturnT<String>(ReturnT.SUCCESS_CODE, sb.toString());
    }

    /**
     * 每月15号检测累计超过param次未能成功访问的友链并删除
     *
     * @param param
     * @return
     */
    @XxlJob("DelLinksHandler")
    public ReturnT<String> delLinks(String param) {
        List<LinksModel> list = linkRecordDao.getAllLinks();
        StringBuilder sb = new StringBuilder();
        list.stream().forEach(e -> {
            //累计超过30次访问不通，则删除友链
            if (e.getTotal() > Integer.valueOf(param)) {
                linksDao.delLinksById(e.getId());
                linkRecordDao.deleteLinkById(e.getId());
                sb.append("<p style=\"color:red\">友链:" + e.getUrl() + "已被删除</p>\n");
            }
        });
        return new ReturnT<String>(ReturnT.SUCCESS_CODE, sb.toString());
    }

    /**
     * 每日同步halo友链信息到link_record表
     *
     * @param param
     * @return
     */
    @XxlJob("InsertLinksHandler")
    public ReturnT<String> insertLinks(String param) {
        List<LinksModel> list = linksDao.getAllLinks();
        Integer[] total = {0};
        list.stream().forEach(e -> {
            try {
                linkRecordDao.insert(e);
            } catch (Exception ee) {
                log.error("插入失败");
                total[0] = total[0] + 1;
            }
        });
        return new ReturnT<String>(ReturnT.SUCCESS_CODE, "<p style=\"color:green\">同步:" + total[0] + "条友链</p>\n");
    }
}

