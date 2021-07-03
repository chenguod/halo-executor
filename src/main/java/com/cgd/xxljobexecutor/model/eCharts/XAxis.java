package com.cgd.xxljobexecutor.model.eCharts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 22:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XAxis {
    private String type;
    private Boolean boundaryGap;
    private List<String> data;
}
