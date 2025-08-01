package com.lbk.sharper.common;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lbk
 * @Date: 2021/11/7 17:45
 * @Description  //铺抓404错误
 * @Version 1.0
 */
@RestController
public class MyErrController implements ErrorController{

    @RequestMapping(value = "/error")
    public Result myError(HttpServletResponse rs, Exception ex) {
       return ResultUtil.define(ResponseEnums.NOT_FOUND.getCode(),ResponseEnums.NOT_FOUND.getMsg(), rs.getStatus());


    }


}