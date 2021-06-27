package com.cgd.xxljobexecutor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/27 10:31
 */
@Data
public class SiteListModel {

    @ApiModelProperty(notes = "应用 ID")
    private String siteId;

    @ApiModelProperty(notes = "站点域名")
    private String domain;

    @ApiModelProperty(notes = "0 : 正常 ; 1: 暂停")
    private Integer status;

    @ApiModelProperty(notes = "日期时间格式, 以北京时间表示")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createTime;
}
