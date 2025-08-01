package com.lbk.sharper.common;

/**
 * @Author: WJX
 * @Date: 2023/6/15 15:09
 * @Description
 * @Version 1.0
 */
public class ResultUtil {
    public static <T> Result<T>  defineSuccess(Integer code, T data) {
        Result result = new Result<>();
        return result.setCode(code).setData(data);
    }

    public static <T> Result<T> success(T data) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS).setData(data);
        return result;
    }

    public static <T> Result<T> ok() {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS);
        return result;
    }

    public static <T> Result<T> fail(String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.FAIL).setMsg(msg);
        return result;
    }

    public static <T> Result<T> defineFail(int code, String msg){
        Result result = new Result();
        result.setCode(code).setMsg(msg);
        return result;
    }

    public static <T> Result<T> defineFail(ResponseEnums responseEnums){
        Result result = new Result();
        result.setCode(responseEnums.getCode()).setMsg(responseEnums.getMsg());
        return result;
    }

    public static <T> Result<T> define(int code, String msg, T data){
        Result result = new Result();
        result.setCode(code).setMsg(msg).setData(data);
        return result;
    }


    public static<T> Result<T> error( ResponseEnums responseEnums) {
        Result result = new Result();
        result.setCode(responseEnums.getCode()).setMsg(responseEnums.getMsg());
        return result;
    }

    public static<T> Result<T> error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code).setMsg(message);
        return result;
    }
    public static<T> Result<T> error(String message) {
        Result result = new Result();
        result.setCode(-1).setMsg(message);
        return result;
    }
}
