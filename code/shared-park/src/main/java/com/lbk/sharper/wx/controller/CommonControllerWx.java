package com.lbk.sharper.wx.controller;

import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @Author: WJX
 * @Date: 2023/6/10 14:36
 * @Description 公共控制器
 * @Version 1.0
 */
@RestController
@RequestMapping("/wx/common")
public class CommonControllerWx {

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    @RequestMapping(value="/deleteImg",method = RequestMethod.GET)
    @ApiOperation(value="删除图片文件，只是删除文件",httpMethod = "GET")
    public Result delFile(String path) {
        File file = new File(UPLOAD_FOLDER + path);
        if (file.exists()) {
            if (file.delete()) {
                return ResultUtil.defineFail(200, "文件删除成功");
            } else {
                return ResultUtil.defineFail(402, "文件删除不成功");
            }
        }
        return ResultUtil.defineFail(402, "文件不存在");
    }
}
