package com.cgd.xxljobexecutor.model.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/3 15:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO {
    @ApiModelProperty(notes = "地区")
    private String name;

    @ApiModelProperty(notes = "浏览总量(PV)")
    private Integer value;
}
