package com.cgd.xxljobexecutor.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/30 20:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteTrendOriginModel {

    @ApiModelProperty(notes = "应用ID")
    private String siteId;

    @ApiModelProperty(notes = "来源地址")
    private String linkOrigin;

    @ApiModelProperty(notes = "浏览量(PV)")
    private Integer pvCount;

    @ApiModelProperty(notes = "占比，%")
    private BigDecimal ratio;

    @ApiModelProperty(notes = "日期, 以北京时间表示")
    private String date;

}
