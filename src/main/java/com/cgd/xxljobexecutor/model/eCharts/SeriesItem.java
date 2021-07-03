package com.cgd.xxljobexecutor.model.eCharts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 22:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeriesItem<T> {
    private String name;
    private String type;
    private String stack;
    private List<T> data;

}
