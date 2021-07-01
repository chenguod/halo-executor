package com.cgd.xxljobexecutor.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/1 18:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteTrendEngineModel {

    @ApiModelProperty(notes = "应用ID")
    private String siteId;

    @ApiModelProperty(notes = "搜索引擎")
    private String searchEngine;

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
}
