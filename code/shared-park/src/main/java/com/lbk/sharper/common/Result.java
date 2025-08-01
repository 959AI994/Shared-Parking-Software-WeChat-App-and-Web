package com.lbk.sharper.common;

/**
 * @Author: WJX
 * @Date: 2023/5/10 15:07
 * @Description
 * @Version 1.0
 */
public class Result <T> {
    private Integer code;
    //操作结果的状态码或代码
    private String msg;
    //与操作结果相关的消息或描述
    private T data;//表示操作的实际数据或结果。它是一个泛型类型T，允许根据需要定义数据的类型。

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {  //重写了默认的toString()方法
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Result setCode(ResultEnum resultEnum) {
        this.code = resultEnum.code;//根据ResultEnum对象设置code属性的值
        return this;
    }
}
