package com.cgd.xxljobexecutor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/5/21 12:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "系统返回消息模板")
public class ResponseMessages<T> {

    private static final String SUCCESS = "000";

    private static final String ERROR = "999";

    @ApiModelProperty(value = "返回码")
    private String code;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "具体数据")
    private T data;

    public static <T> ResponseMessages SUCCESS(T data){
        return new ResponseMessages(SUCCESS,"ok",data);
    }

    public static <T> ResponseMessages FAIL(T data){
        return new ResponseMessages(ERROR,"fail",data);
    }
}
