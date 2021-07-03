package com.cgd.xxljobexecutor.model.eCharts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 22:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grid {
    private String left;
    private String right;
    private String bottom;
    private Boolean containLabel;
}
