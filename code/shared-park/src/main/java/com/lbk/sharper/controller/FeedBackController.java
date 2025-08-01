package com.lbk.sharper.controller;


import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.service.IFeedBackService;
import com.lbk.sharper.vo.FeedBackVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-5-16
 */
@RestController
@RequestMapping("/feed-back")
public class FeedBackController {
    @Autowired//这个注解用于自动注入IFeedBackService接口的实现对象，用于处理反馈信息的业务逻辑。
    IFeedBackService iFeedBackService;

    @RequestMapping(value="/getPageList",method = RequestMethod.POST)
    @ApiOperation(value="获取反馈分页信息",httpMethod = "POST")
    //iFeedBackService.getPageList(vo)方法来获取反馈信息的分页结果
    public Result getPageList(FeedBackVo vo) {
        return ResultUtil.success(iFeedBackService.getPageList(vo));
    }

    @RequestMapping(value="/delete",method = RequestMethod.GET)
    @ApiOperation(value="删除反馈信息",httpMethod = "GET")
    public Result delete(Integer id) {
        return ResultUtil.success(iFeedBackService.delete(id));
    }


    @GetMapping(value="/count")
    @ApiOperation(value="获取总反馈数量",httpMethod = "GET")
    public Result count() {
        return ResultUtil.success(iFeedBackService.count());
    }

}
//将操作结果封装在结果对象中并返回，有助于提供一致的响应格式、操作状态信息和错误处理机制，同时也提供了扩展性和规范性。
// 这样可以提高代码的可读性、维护性和可扩展性，同时也方便了客户端对结果的处理和解析。
