package com.lbk.sharper.wx.controller;



import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.wx.annotation.Login;
import com.lbk.sharper.wx.annotation.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP测试接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/wx/")
@Api("wx测试接口")
public class AppTestController {

    @Login
    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public Result userInfo(@LoginUser NormalUser user){

        return ResultUtil.success("user:" + user);
    }

    @Login
    @GetMapping("openId")
    @ApiOperation("获取用户ID")
    public Result userInfo(@RequestAttribute("openId") String openId){
        return ResultUtil.success("userId: "+openId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public Result notToken(){
        return ResultUtil.success("okk");
    }

}
