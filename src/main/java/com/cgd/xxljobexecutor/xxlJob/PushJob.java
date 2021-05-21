package com.cgd.xxljobexecutor.xxlJob;

import com.cgd.xxljobexecutor.utils.HttpRequestUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/19 15:24
 */
@Component
public class PushJob {

    @XxlJob("PushJobHandler")
    public ReturnT<String> PushJobHandler(String param){
        String s = HttpRequestUtil.sendGet("https://halo.chenmx.net/sitemap.xml","");
        return ReturnT.SUCCESS;
    }
}
