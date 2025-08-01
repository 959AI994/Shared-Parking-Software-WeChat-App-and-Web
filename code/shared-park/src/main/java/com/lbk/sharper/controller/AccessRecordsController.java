package com.lbk.sharper.controller;


import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.service.IAccessRecordsService;
import com.lbk.sharper.vo.AccessRecordsVo;
import com.lbk.sharper.vo.TestVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 出入小区记录 前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
@RestController
@RequestMapping("/access")
public class AccessRecordsController {
    @Autowired
    IAccessRecordsService iAccessRecordsService;
//处理获取出入小区记录分页信息的请求。根据传入的 AccessRecordsVo 对象，
// 调用 iAccessRecordsService 的 getPageList 方法获取出入小区记录的分页列表，并将结果封装为 Result 对象返回。
    //指定了处理请求的路径和请求方法。
    @RequestMapping(value="/getPageList",method = RequestMethod.POST)//当发送一个 POST 请求到 "/access/getPageList" 路径时，将会由 getPageList 方法处理该请求。
    //指定了请求路径为 "/getPageList"，即对应的请求 URL 为 "/access/getPageList"。
    @ApiOperation(value="获取出入小区记录分页信息",httpMethod = "POST")
    public Result getPageList(AccessRecordsVo accessRecordsVo) {
        //success方法用于创建一个表示操作成功的 Result 对象。它通常用于封装操作的结果并返回给调用方。
        return ResultUtil.success(iAccessRecordsService.getPageList(accessRecordsVo));
    }
    
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ApiOperation(value="添加出入小区记录信息", httpMethod = "POST")
    public Result addRecord(AccessRecordsVo accessRecordsVo) {
        return ResultUtil.success(iAccessRecordsService.add(accessRecordsVo));
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ApiOperation(value="修改出入小区记录信息", httpMethod = "POST")
    public Result update(AccessRecordsVo accessRecordsVo) {
        return ResultUtil.success(iAccessRecordsService.updates(accessRecordsVo));
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    //将delete方法映射到/delete的POST请求。
    @ApiOperation(value="删除出入小区记录信息", httpMethod = "POST")
    public Result delete(AccessRecordsVo accessRecordsVo) {
        return ResultUtil.success(iAccessRecordsService.delete(accessRecordsVo));
    }
    
    
    @GetMapping("/QrTest") //将get方法映射到/QrTest的GET请求。
    @ApiOperation(value="测试生成二维码", httpMethod = "GET")
    //这是一个生成二维码的方法。它接收一个名为response的参数，类型为HttpServletResponse，
    // 并在方法体中使用response对象获取输出流。
    // 然后，根据指定的宽度、高度、格式和内容，使用MultiFormatWriter类生成一个二维码，
    // 并将二维码写入输出流中，最后输出到页面。
    public void get(HttpServletResponse response) throws Exception {
        int width = 200;
        int height = 200;
        String format = "png";
        String content = "120211109005521902795";
        ServletOutputStream out = response.getOutputStream();
        Map<EncodeHintType,Object> config = new HashMap<>();
        config.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        config.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        config.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,config);
        MatrixToImageWriter.writeToStream(bitMatrix,format,out);
        System.out.println("二维码生成完毕，已经输出到页面中。");
    }

    
    



}
