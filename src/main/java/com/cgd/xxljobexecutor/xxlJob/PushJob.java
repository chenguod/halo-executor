package com.cgd.xxljobexecutor.xxlJob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author chengd
 * @version 1.0
 * @date 2021/5/19 15:24
 */
@Component
public class PushJob {

    @XxlJob("PushJobHandler")
    public ReturnT<String> PushJobHandler(String param){
        ReturnT<String> returnT = new ReturnT<>();
        returnT.setCode(ReturnT.SUCCESS_CODE);
        returnT.setContent("demo");
        returnT.setMsg("<p style=\"color:red\">test</p>");
        return returnT;
    }
}
