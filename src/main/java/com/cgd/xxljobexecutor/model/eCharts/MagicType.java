package com.cgd.xxljobexecutor.model.eCharts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/3 11:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagicType {
    private Boolean show;
    private List<String> type;
}
