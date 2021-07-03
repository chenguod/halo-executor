package com.cgd.xxljobexecutor.model.eCharts;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 22:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Title {

    @ApiModelProperty(notes = "主标题文本")
    private String text;

    private String left;

    private String top;
}
