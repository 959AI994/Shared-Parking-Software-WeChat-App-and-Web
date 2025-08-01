package com.lbk.sharper.controller;

import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.common.validator.Assert;
import com.lbk.sharper.entity.SysMenu;
import com.lbk.sharper.entity.SysUser;
import com.lbk.sharper.service.SysUserService;
import com.lbk.sharper.util.JwtUtil;
import com.lbk.sharper.util.MapUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author WJX
 * 后台管理登录
 */
@RestController//这个注解表示这是一个控制器类，并且响应的结果将以JSON格式返回。
@RequestMapping("/sys")//这个注解将控制器的根路径设置为"/sys"，表示处理以"/sys"开头的请求。
public class LoginController {
    @Autowired
    SysUserService sysUserService;
//前端的东西发到这里，后端这边的代码进行校验，并且向数据库请求。
    @PostMapping("/login")//这个注解将login方法映射到"/login"的POST请求。
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
// 这是一个处理登录请求的方法。它接收两个请求参数：username和password，分别表示用户名和密码。
        Assert.isBlank(username,"用户名不能为空");
        Assert.isBlank(password,"密码不能为空");
        //这两行代码用于校验用户名和密码是否为空，如果为空，则抛出异常。
        SysUser user = sysUserService.getPassword(username);  //这一步需要请求数据库
//根据用户名从数据库中获取用户信息。
        //如果用户名错误，则返回一个表示失败的 Result 对象；
        // 如果密码错误，则返回一个表示失败的 Result 对象。
        if (user == null) {
            return ResultUtil.fail("用户名错误");
        } else if (!user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            return ResultUtil.fail("密码错误");
        }
            return ResultUtil.success(JwtUtil.createToken(String.valueOf(user.getUserId())));
//            return ResultUtil.success("vue_admin_template_token");

    }
}
