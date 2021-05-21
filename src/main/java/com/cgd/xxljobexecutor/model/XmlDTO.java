package com.cgd.xxljobexecutor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 7:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class XmlDTO {

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "time")
    private String time;
}
