package com.lbk.sharper.wx.controller;


import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.service.IFeedBackService;
import com.lbk.sharper.vo.FeedBackVo;
import com.lbk.sharper.wx.annotation.Login;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-5-26
 */
@RestController
@RequestMapping("/wx/feed-back")
public class FeedBackControllerWx {
    @Autowired
    IFeedBackService iFeedBackService;


    @Login
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ApiOperation(value="添加用户反馈信息", httpMethod = "POST")
    public Result addRecord(FeedBackVo feedBackVo, @RequestAttribute("openId") String openId) {
        return ResultUtil.success(iFeedBackService.add(feedBackVo,openId));
    }

}
