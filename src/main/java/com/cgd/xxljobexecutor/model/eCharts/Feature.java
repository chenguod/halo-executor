package com.cgd.xxljobexecutor.model.eCharts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/3 11:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feature {
    private MagicType magicType;
    private SaveAsImage saveAsImage;
    private Restore restore;
}
