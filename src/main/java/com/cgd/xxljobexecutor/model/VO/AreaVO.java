package com.cgd.xxljobexecutor.model.VO;

import com.cgd.xxljobexecutor.model.DTO.AreaDTO;
import com.cgd.xxljobexecutor.model.eCharts.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/3 15:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaVO<T> {

    private Title title;

    private Tooltip  tooltip;

    private Legend legend;

    private Toolbox toolbox;

    private List<Series<T>> series;
}
