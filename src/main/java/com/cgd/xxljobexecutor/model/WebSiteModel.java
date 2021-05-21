package com.cgd.xxljobexecutor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 13:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteModel {

    private Integer id;

    private String url;

    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date importDate;
}
