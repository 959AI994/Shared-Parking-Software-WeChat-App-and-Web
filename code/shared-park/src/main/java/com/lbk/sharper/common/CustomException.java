package com.lbk.sharper.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lbk
 * @Date: 2021/11/7 16:47
 * @Description 自定义统一异常（相当于业务异常）
 * @Version 1.0
 */
@Data
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    public CustomException() {
        super();
    }
    /**
     * @description 构造器
     * @param code 异常状态码
     * @param msg 异常返回信息
     */
    public CustomException(Integer code,  String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }


    public CustomException(ResponseEnums responseEnums) {
        super(responseEnums.getMsg());
        this.msg = responseEnums.getMsg();
        this.code = responseEnums.getCode();
    }

    public CustomException(String errorMsg) {
        super(errorMsg);
        this.code = -1;
        this.msg = errorMsg;
    }

    public CustomException(Integer errorCode, String errorMsg, Throwable cause) {
        super(String.valueOf(errorCode), cause);
        this.code = errorCode;
        this.msg = errorMsg;
    }


}
