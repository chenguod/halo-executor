package com.cgd.xxljobexecutor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 16:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteDetailModel {

    private Integer id;

    private Integer pId;

    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date CreateDate;

    private Integer pushFlag;
}
