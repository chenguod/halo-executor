package com.cgd.xxljobexecutor.model.eCharts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 22:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Legend {
    private String orient;
    private List<String> data;
    private String top;
    private String left;
}
