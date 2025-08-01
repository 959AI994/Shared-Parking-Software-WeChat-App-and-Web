package com.lbk.sharper.controller;

import com.lbk.sharper.common.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WJX
 */
@RestController//这个注解表示这是一个控制器类，并且响应的结果将以JSON格式返回。
@RequestMapping("/guest")
public class GuestController {
    private final ResultMap resultMap;

    @Autowired
    public GuestController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @GetMapping("/welcome")
    //这行代码使用resultMap对象调用连续的方法，设置登录成功的状态码为200，
    // 设置欢迎消息为"欢迎访问游客页面！"，并将结果返回。
    public ResultMap login() {
        return resultMap.success().code(200).message("欢迎访问游客页面！");
    }
}
