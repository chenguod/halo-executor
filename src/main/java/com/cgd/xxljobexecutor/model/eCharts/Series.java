package com.cgd.xxljobexecutor.model.eCharts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 22:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Series<T> {
    private String name;
    private String type;
    private String stack;
    private String radius;
    private List<T> data;
    private Emphasis emphasis;

    private String selectedMode;
    private LabelLine labelLine;

    public Series(String name, String type, String stack, String radius, List<T> data, Emphasis emphasis,LabelLine labelLine) {
        this.name = name;
        this.type = type;
        this.stack = stack;
        this.radius = radius;
        this.data = data;
        this.emphasis = emphasis;
        this.labelLine = labelLine;
    }
    public Series(String name, String type, String stack, String radius, List<T> data, Emphasis emphasis) {
        this.name = name;
        this.type = type;
        this.stack = stack;
        this.radius = radius;
        this.data = data;
        this.emphasis = emphasis;
    }
}
