package com.lbk.sharper.wx.controller;


import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.req.OrderFrom;
import com.lbk.sharper.service.IParkingOrderService;
import com.lbk.sharper.vo.BaseVo;
import com.lbk.sharper.vo.ParkingOrderVo;
import com.lbk.sharper.wx.annotation.Login;
import com.lbk.sharper.wx.wxvo.BasePageVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 停车订单 前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-5-18
 */
@RestController
@RequestMapping("/wx/parkingOrder")
public class ParkingOrderControllerWx {
    @Autowired
    IParkingOrderService iParkingOrderService;

    @Login
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ApiOperation(value="添加停车信息", httpMethod = "POST")
    public Result addReservation(@RequestAttribute("openId") String openId, @Validated OrderFrom vo) {
        return ResultUtil.success(iParkingOrderService.add(vo,openId));
    }

    @Login
    @RequestMapping(value="/getMyReservationInfo", method = RequestMethod.POST)
    @ApiOperation(value="查询我的全部停车信息", httpMethod = "POST")
    public Result getMyReservationInfo(@RequestAttribute("openId") String openId, BasePageVo baseVo) {
        return ResultUtil.success(iParkingOrderService.getMyReservationInfo(baseVo,openId));
    }

    @Login
    @RequestMapping(value="/findMyForReservation", method = RequestMethod.POST)
    @ApiOperation(value="订单进行中停车信息", httpMethod = "POST")
    public Result findMyForReservation(@RequestAttribute("openId") String openId, BasePageVo basePageVo) {
        return ResultUtil.success(iParkingOrderService.findMyForReservation(basePageVo,openId));
    }

    @Login
    @RequestMapping(value="/findMyFinishReservation", method = RequestMethod.POST)
    @ApiOperation(value="订单已完成停车信息", httpMethod = "POST")
    public Result findMyFinishReservation(@RequestAttribute("openId") String openId, BasePageVo basePageVo) {
        return ResultUtil.success(iParkingOrderService.findMyFinishReservation(basePageVo,openId));
    }

    @RequestMapping(value="/delete", method = RequestMethod.GET)
    @ApiOperation(value="删除订单", httpMethod = "GET")
    public Result delete(String id) {
        return ResultUtil.success(iParkingOrderService.delete(id));
    }

    @RequestMapping(value="/confirmApproach", method = RequestMethod.GET)
    @ApiOperation(value="订单确认入场", httpMethod = "GET")
    public Result confirmApproach(String orderId) {
        return ResultUtil.success(iParkingOrderService.confirmApproach(orderId));
    }

    @Login
    @RequestMapping(value="/iSharedRecords", method = RequestMethod.GET)
    @ApiOperation(value="查询我的共享记录", httpMethod = "GET")
    public Result iSharedRecords(@RequestAttribute("openId") String openId) {
        return ResultUtil.success(iParkingOrderService.iSharedRecords(openId));
    }

    @RequestMapping(value="/cancellation", method = RequestMethod.GET)
    @ApiOperation(value="取消预约停车", httpMethod = "GET")
    public Result cancellation(String orderId) {
        return ResultUtil.success(iParkingOrderService.cancellationOrder(orderId));
    }

    @RequestMapping(value="/submitSettlementOrder", method = RequestMethod.GET)
    @ApiOperation(value="提交结算订单，返回订单信息", httpMethod = "GET")
    public Result submitSettlementOrder(String orderId) {
        return ResultUtil.success(iParkingOrderService.submitSettlementOrder(orderId));
    }

    @RequestMapping(value="/whetherAdmission", method = RequestMethod.GET)
    @ApiOperation(value="是否确认入场", httpMethod = "GET")
    public Result whetherAdmission(String orderId) {
        return ResultUtil.success(iParkingOrderService.whetherAdmission(orderId));
    }

    @RequestMapping(value="/getOrderInfo", method = RequestMethod.GET)
    @ApiOperation(value="根据orderId查询一条订单记录", httpMethod = "GET")
    public Result getOrderInfo(String orderId) {
        return ResultUtil.success(iParkingOrderService.getParkingOrder(orderId));
    }

    @RequestMapping(value="/getAppointmentOrderInfo", method = RequestMethod.GET)
    @ApiOperation(value="根据ID查询预约订单记录", httpMethod = "GET")
    public Result getAppointmentOrderInfo(String orderId) {
        return ResultUtil.success(iParkingOrderService.getOrderInfo(orderId));
    }

    @Login
    @RequestMapping(value="/payment", method = RequestMethod.GET)
    @ApiOperation(value="支付订单", httpMethod = "GET")
    public Result payment(String orderId,@RequestAttribute("openId")String openId) {
            return ResultUtil.success(iParkingOrderService.payment(orderId,openId));
    }
}
