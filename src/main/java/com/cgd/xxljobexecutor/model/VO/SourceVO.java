package com.cgd.xxljobexecutor.model.VO;

import com.cgd.xxljobexecutor.model.eCharts.Legend;
import com.cgd.xxljobexecutor.model.eCharts.Series;
import com.cgd.xxljobexecutor.model.eCharts.Title;
import com.cgd.xxljobexecutor.model.eCharts.Tooltip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Timer;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/4 9:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceVO<T> {
    private Title title;
    private Tooltip tooltip;
    private Legend legend;
    private List<Series<T>> series;
}