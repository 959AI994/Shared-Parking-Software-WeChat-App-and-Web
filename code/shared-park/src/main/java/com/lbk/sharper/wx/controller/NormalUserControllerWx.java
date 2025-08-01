package com.lbk.sharper.wx.controller;


import com.lbk.sharper.common.BizState;
import com.lbk.sharper.common.CustomException;
import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.req.UserAuthFrom;
import com.lbk.sharper.service.INormalUserService;
import com.lbk.sharper.util.JwtUtil;
import com.lbk.sharper.util.OpenIdJson;
import com.lbk.sharper.vo.UserVo;
import com.lbk.sharper.wx.annotation.Login;
import com.lbk.sharper.wx.annotation.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 普通用户表 前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-6-3
 */
@RestController
@RequestMapping("/wx/user")   //这个路径是头部，login是尾部
public class NormalUserControllerWx {
    @Autowired
    INormalUserService iNormaluserService;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ApiOperation(value="微信用户登录",httpMethod = "POST")
    public Result weChatLogin(UserVo userVo) {
        if(userVo.getCode()==null){
            throw new CustomException("code不能为空！");
        }
        //用code获取openID
         OpenIdJson openIdJson= iNormaluserService.getOpenId(userVo.getCode());
        NormalUser user = iNormaluserService.getUserInfo(openIdJson.getOpenid());
        if (user!=null){
            //返回用户信息
            return ResultUtil.success(JwtUtil.createToken(openIdJson.getOpenid()));
        }
        userVo.setOpenId(openIdJson.getOpenid());
        userVo.setState(BizState.normal);
        userVo.setBalance((double) 500.56);
        userVo.setIsOwner(0);
        userVo.setRole_id(4);
        //进行用户注册
        if(!iNormaluserService.save(userVo)){
            throw new CustomException("注册失败");
        }
        return ResultUtil.defineSuccess(202,JwtUtil.createToken(openIdJson.getOpenid()));
    }

    @Login
    @RequestMapping(value="/getUser",method = RequestMethod.GET)
    @ApiOperation(value="查询用户信息",httpMethod = "GET")
    public Result getUser(@LoginUser NormalUser user) {
        return ResultUtil.success(user);
    }

    @Login
    @RequestMapping(value="/userAuth",method = RequestMethod.POST)
    @ApiOperation(value="完善用户信息",httpMethod = "POST")
    public Result userAuth(@RequestAttribute("openId") String openId,@Validated UserAuthFrom userAuthFrom) {
        return ResultUtil.success(iNormaluserService.userAuth(userAuthFrom,openId));
    }
}
