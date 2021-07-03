package com.cgd.xxljobexecutor.model.DTO;

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
@ApiModel(value = "XmlDTO", description = "站点地图详情")
public class XmlDTO {

    @ApiModelProperty(value = "链接")
    private String loc;

    @ApiModelProperty(value = "时间")
    private String lastmod;
}
