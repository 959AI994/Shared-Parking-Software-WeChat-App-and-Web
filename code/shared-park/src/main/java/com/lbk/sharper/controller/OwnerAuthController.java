package com.lbk.sharper.controller;


import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.service.IOwnerAuthService;
import com.lbk.sharper.vo.OwnerAuthVo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 * 业主信息 前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-6-01
 */
@RestController
@RequestMapping("/ownerAuth")
public class OwnerAuthController {
    @Autowired
    IOwnerAuthService iOwnerAuthService;

    @RequestMapping(value="/getPageList",method = RequestMethod.POST)
    @ApiOperation(value="获取业主分页信息",httpMethod = "POST")
    public Result getPageList(OwnerAuthVo ownerAuthVo) {
        return ResultUtil.success(iOwnerAuthService.getPageList(ownerAuthVo));
    }

    @RequestMapping(value="/getList",method = RequestMethod.GET)
    @ApiOperation(value="获取全部业主信息",httpMethod = "GET")
    public Result getList() {
        return ResultUtil.success(iOwnerAuthService.getList());
    }


    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ApiOperation(value="修改业主信息", httpMethod = "POST")
    public Result update(OwnerAuthVo ownerAuthVo) {
        return ResultUtil.success(iOwnerAuthService.updates(ownerAuthVo));
    }


    @RequestMapping(value="/arrces", method = RequestMethod.POST)
    @ApiOperation(value="认证信息", httpMethod = "POST")
    public Result arrces(OwnerAuthVo ownerAuthVo) {
        return ResultUtil.success(iOwnerAuthService.arrces(ownerAuthVo));
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
