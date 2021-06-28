package com.cgd.xxljobexecutor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/28 13:10
 */
@Data
public class SiteTrendModel {

    @ApiModelProperty(notes = "应用ID")
    private String siteId;

    @ApiModelProperty(notes = "浏览量(PV)")
    private Integer pvCount;

    @ApiModelProperty(notes = "浏览量占比，%")
    private BigDecimal pvRatio;

    @ApiModelProperty(notes = "访问次数")
    private Integer visitCount;

    @ApiModelProperty(notes = "访客数(UV)")
    private Integer visitorCount;

    @ApiModelProperty(notes = "新访客数")
    private Integer newVisitorCount;

    @ApiModelProperty(notes = "新访客比率，%")
    private BigDecimal newVisitorRatio;

    @ApiModelProperty(notes = "IP 数")
    private Integer ipCount;

    @ApiModelProperty(notes = "跳出率，%")
    private BigDecimal bounceRatio;

    @ApiModelProperty(notes = "平均访问时长，秒")
    private Integer avgVisitTime;

    @ApiModelProperty(notes = "平均访问时长，秒")
    private Integer avgVisitPages;

    @ApiModelProperty(notes = "日期, 以北京时间表示")
    private String date;
}
