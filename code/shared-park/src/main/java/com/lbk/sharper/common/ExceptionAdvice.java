package com.lbk.sharper.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * @author LBK
 * @Description 异常处理
 * @Date 2019-04-09
 * @Time 17:09
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {

        if (e instanceof ShiroException) {
            ShiroException ex = (ShiroException) e;
            log.info("shiro异常：msg：" + ex.getMessage() + "，log：" + ex.getLocalizedMessage(), e);
            return ResultUtil.defineFail(ResponseEnums.NOT_PERMISSION.getCode(), ResponseEnums.NOT_PERMISSION.getMsg());
        } else if (e instanceof CustomException) {
            CustomException ex = (CustomException) e;
            log.info("自定义业务异常：msg：" + ex.getCode() + "，log：" + ex.getMessage(), e);
            return ResultUtil.define(ex.getCode(), ex.getMessage(), null);
        } else if (e instanceof NullPointerException) {
            NullPointerException ex = (NullPointerException) e;
            log.error("发生空指针异常：msg：" + ex.getMessage(), ex);
            return ResultUtil.defineFail(ResponseEnums.NULL_ERROR.getCode(), ResponseEnums.NULL_ERROR.getMsg());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            ex.printStackTrace();
            log.error("validation注解异常：msg：" + ex.getBindingResult().getFieldError().getDefaultMessage(), e);
            return ResultUtil.defineFail(-1, ex.getBindingResult().getFieldError().getDefaultMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = ex.getBindingResult();
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder errorMsg = new StringBuilder();
            for (FieldError error : errors) {
                errorMsg.append(error.getField());
                errorMsg.append(":");
                errorMsg.append(error.getDefaultMessage());
                errorMsg.append("; ");
            }
            log.error("MethodArgumentNotValidException异常：msg：" + errorMsg, e);
            return ResultUtil.defineFail(-1, ex.getBindingResult().getFieldError().getDefaultMessage());
        } else {
            log.error("统一系统异常：msg：" + e.getMessage(), e);
            return ResultUtil.define(ResponseEnums.EXCEPTION.getCode(), e.getMessage(), e.getLocalizedMessage());
        }

    }


}
