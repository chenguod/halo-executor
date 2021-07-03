package com.cgd.xxljobexecutor.model.DTO;

import com.cgd.xxljobexecutor.model.eCharts.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 23:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitVO<T> {
    private Title title;
    private Tooltip tooltip;
    private Legend legend;
    @JsonProperty
    private Toolbox toolbox;
    private Grid grid;
    @JsonProperty
    private XAxis xAxis;
    @JsonProperty
    private YAxis yAxis;
    private List<Series<T>> series;
}
