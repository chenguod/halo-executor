package com.cgd.xxljobexecutor.model.eCharts;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 22:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tooltip {

    @ApiModelProperty(notes = "触发类型")
    private String trigger;

    private String formatter;

    public Tooltip(String trigger) {
        this.trigger = trigger;
    }
}
