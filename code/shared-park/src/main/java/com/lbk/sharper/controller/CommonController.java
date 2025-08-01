package com.lbk.sharper.controller;

import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @Author: WJX
 * @Date: 2023/6/10 14:36
 * @Description 公共控制器
 * @Version 1.0
 */
@RestController
//该类是一个RESTful风格的控制器，它将处理HTTP请求并返回RESTful响应。
@RequestMapping("/common")
public class CommonController {

    @Value("${prop.upload-folder}")//这个注解用于将配置文件中的属性值注入到UPLOAD_FOLDER变量中。
    private String UPLOAD_FOLDER;

    @RequestMapping(value="/deleteImg",method = RequestMethod.GET)
    @ApiOperation(value="删除图片文件，只是删除文件",httpMethod = "GET")
    public Result delFile(String path) {
        //接收一个名为path的参数，表示要删除的文件路径。
        // 在方法内部，它根据给定的路径构建完整的图片路径img_path，然后创建一个File对象。
        String img_path = UPLOAD_FOLDER + path;
        File file = new File(img_path);
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
