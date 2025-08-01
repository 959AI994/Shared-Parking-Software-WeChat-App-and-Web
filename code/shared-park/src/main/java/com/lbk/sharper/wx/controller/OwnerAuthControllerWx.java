package com.lbk.sharper.wx.controller;


import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.service.IOwnerAuthService;
import com.lbk.sharper.vo.OwnerAuthVo;
import com.lbk.sharper.wx.annotation.Login;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 * 业主信息 前端控制器
 * </p>
 *
 * @author LBK
 * @since 2021-10-15
 */
@RestController
@RequestMapping("/wx/ownerAuth")
public class OwnerAuthControllerWx {
    @Autowired
    IOwnerAuthService iOwnerAuthService;

    @RequestMapping(value="/getList",method = RequestMethod.GET)
    @ApiOperation(value="获取全部业主信息",httpMethod = "GET")
    public Result getList() {
        return ResultUtil.success(iOwnerAuthService.getList());
    }


    @Login // @Login 注解可能包含了登录验证的逻辑，用于验证用户的身份和权限。只有经过登录认证的用户才能执行被该注解修饰的方法，从而保证了该方法的安全性和可访问性。
    @RequestMapping(value="/getOwnerAuth",method = RequestMethod.GET)
    @ApiOperation(value="查询业主信息",httpMethod = "GET")
    public Result getOwnerAuth(@RequestAttribute("openId") String openId) {
        return ResultUtil.success(iOwnerAuthService.getOwnerAuth(openId));
    }

    /**
     * 小程序业主提交申请材料以后，会调用这个函数，把业主提交的信息放到数据库里
     * @param openId
     * @param ownerAuthVo
     * @return
     */
    @Login // @Login 注解可能包含了登录验证的逻辑，用于验证用户的身份和权限。只有经过登录认证的用户才能执行被该注解修饰的方法，从而保证了该方法的安全性和可访问性。
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ApiOperation(value="添加业主信息", httpMethod = "POST")
    public Result addRecord(@RequestAttribute("openId") String openId,OwnerAuthVo ownerAuthVo) {
        return ResultUtil.success(iOwnerAuthService.add(ownerAuthVo,openId));
    }


    @RequestMapping(value="/delete", method = RequestMethod.POST)
    @ApiOperation(value="删除业主信息", httpMethod = "POST")
    public Result delete(OwnerAuthVo ownerAuthVo) {
        return ResultUtil.success(iOwnerAuthService.delete(ownerAuthVo));
    }

    @PostMapping("/upload")
    @ApiOperation(value="上传业主证明图片", httpMethod = "POST")
    public Result upload(@RequestParam(name = "file", required = false) MultipartFile files) {
        return iOwnerAuthService.uploadImg(files);
    }



}
