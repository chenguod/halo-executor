package com.cgd.xxljobexecutor.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/2 18:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {

    private String code;

    private String message;

    private T data;

    public static String SUCCESS_CODE = "000";

    public static String FAIL_CODE = "999";

    public static ResponseMessage<String> SUCCESS = new ResponseMessage<>(SUCCESS_CODE,null,null);
    public static ResponseMessage<String> FAIL = new ResponseMessage<>(FAIL_CODE,null,null);
}
