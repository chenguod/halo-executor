package com.cgd.xxljobexecutor.model.DTO;

import com.cgd.xxljobexecutor.model.eCharts.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.Yaml;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 19:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitDTO {

    @ApiModelProperty(notes = "浏览量(PV)")
    private Integer pvCount;

    @ApiModelProperty(notes = "访问次数")
    private Integer visitCount;

    @ApiModelProperty(notes = "访客数(UV)")
    private Integer visitorCount;

    @ApiModelProperty(notes = "新访客数")
    private Integer newVisitorCount;

    @ApiModelProperty(notes = "IP 数")
    private Integer ipCount;

    @ApiModelProperty(notes = "月份")
    private String month;
}
