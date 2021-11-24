package com.cgd.xxljobexecutor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Default;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/10/24 14:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinksModel {
    private int id;
    private String url;
    private int isNormal;
    private int total;
    private int isVaild;
}
