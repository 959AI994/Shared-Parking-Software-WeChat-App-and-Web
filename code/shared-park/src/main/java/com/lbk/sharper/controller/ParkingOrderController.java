package com.lbk.sharper.controller;


import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.service.IParkingOrderService;
import com.lbk.sharper.req.OrderFrom;
import com.lbk.sharper.vo.LicensePlate;
import com.lbk.sharper.vo.ParkingOrderVo;
import com.lbk.sharper.vo.ReportConditionVo;
import com.lbk.sharper.webreq.SpaceWebVo;
import com.lbk.sharper.webreq.UpdateOrderVo;
import com.lbk.sharper.wx.annotation.Login;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 停车订单 前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-5-19
 */
@RestController
@RequestMapping("/parkingOrder")
public class ParkingOrderController {
    @Autowired
    IParkingOrderService iParkingOrderService;

    @RequestMapping(value="/getPageList",method = RequestMethod.POST)
    @ApiOperation(value="分页查询全部停车订单信息",httpMethod = "POST")
    public Result getPageList(ParkingOrderVo vo) {
        return ResultUtil.success(iParkingOrderService.getPageList(vo));
    }

    @RequestMapping(value="/delete", method = RequestMethod.GET)
    @ApiOperation(value="删除订单", httpMethod = "GET")
    public Result delete(String id) {
        return ResultUtil.success(iParkingOrderService.delete(id));
    }


    @RequestMapping(value="/whetherAdmission", method = RequestMethod.GET)
    @ApiOperation(value="是否确认入场", httpMethod = "GET")
    public Result whetherAdmission(String orderId) {
        return ResultUtil.success(iParkingOrderService.whetherAdmission(orderId));
    }

    @GetMapping(value="/count")
    @ApiOperation(value="获取订单数",httpMethod = "GET")
    public Result count() {
        return ResultUtil.success(iParkingOrderService.count());
    }

    @PostMapping(value="/incomeStatement")
    @ApiOperation(value="财务报表统计",httpMethod = "POST")
    public Result incomeStatement(ReportConditionVo vo) {
        return ResultUtil.success(iParkingOrderService.incomeStatement(vo));
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ApiOperation(value="修改订单信息", httpMethod = "POST")
    public Result update(UpdateOrderVo vo) {
        return ResultUtil.success(iParkingOrderService.updates(vo));
    }

    @PostMapping(value="/orderReport")
    @ApiOperation(value="订单报表统计",httpMethod = "POST")
    public Result orderReport(ReportConditionVo vo) {   //调用service从后端请求数据
        return ResultUtil.success(iParkingOrderService.orderReport(vo));
    }


    @PostMapping(value="/identificationPlateIntoThe")
    @ApiOperation(value="识别车牌进入小区",httpMethod = "POST")
    public Result identificationPlateIntoThe(LicensePlate licensePlate) {
        return ResultUtil.success(iParkingOrderService.identificationPlateIntoThe(licensePlate));
    }

    @PostMapping(value="/orderConfirmationForSettlement")
    @ApiOperation(value="识别车牌出小区，结算订单",httpMethod = "POST")
    public Result orderConfirmationForSettlement(LicensePlate licensePlate) {
        return ResultUtil.success(iParkingOrderService.orderConfirmationForSettlement(licensePlate));
    }

}
