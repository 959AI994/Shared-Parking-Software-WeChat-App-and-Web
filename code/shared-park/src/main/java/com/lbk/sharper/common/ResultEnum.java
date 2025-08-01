package com.lbk.sharper.common;

/**
 * @Author: lbk
 * @Date: 2021/9/25 15:08
 * @Description
 * @Version 1.0
 */
public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAIL(400),
    /**
     * 接口不存在
     */
    NOT_FOUND(404),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);
    public int code;
    ResultEnum(int code) {
        this.code = code;
    }
}
