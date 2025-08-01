package com.lbk.sharper.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbk.sharper.common.*;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.req.UserAuthFrom;
import com.lbk.sharper.service.INormalUserService;
import com.lbk.sharper.util.HttpUtil;
import com.lbk.sharper.util.JwtUtil;
import com.lbk.sharper.util.OpenIdJson;
import com.lbk.sharper.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 普通用户表 前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-6-1
 */
//用于处理与普通用户信息相关的HTTP请求。
@RestController
@RequestMapping("/user")
public class NormalUserController {
    @Autowired//INormalUserService对象自动注入到NormalUserController类中，以便在类的其他方法中使用。
    INormalUserService iNormaluserService;


    @RequestMapping(value="/getPageList",method = RequestMethod.POST)
    @ApiOperation(value="分页查询用户信息",httpMethod = "POST")
    public Result getPageList(UserVo vo) {
        return ResultUtil.success(iNormaluserService.getPageList(vo));
    }
    //方法来获取用户信息的分页结果，并将结果封装到Result对象中返回。

    @GetMapping(value="/count")
    @ApiOperation(value="获取总用户数",httpMethod = "GET")
    public Result count() {
        return ResultUtil.success(iNormaluserService.count());
    }
    // 这是一个处理获取总用户数的方法。
    // 它调用iNormaluserService.count()方法来获取总用户数，并将结果封装到Result对象中返回。

}
