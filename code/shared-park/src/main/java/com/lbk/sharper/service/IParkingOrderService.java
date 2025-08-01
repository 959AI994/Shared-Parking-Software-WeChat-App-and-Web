package com.lbk.sharper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lbk.sharper.entity.ParkingOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbk.sharper.res.ResParkingOrderVO;
import com.lbk.sharper.req.OrderFrom;
import com.lbk.sharper.vo.BaseVo;
import com.lbk.sharper.vo.LicensePlate;
import com.lbk.sharper.vo.ParkingOrderVo;
import com.lbk.sharper.vo.ReportConditionVo;
import com.lbk.sharper.webreq.UpdateOrderVo;
import com.lbk.sharper.wx.wxvo.BasePageVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 停车订单 服务类
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
public interface IParkingOrderService extends IService<ParkingOrder> {

    /**
     * 添加订单记录
     * @param orderFrom
     */
    String add(OrderFrom orderFrom,String openId);


    /**
     * 管理员分页查询预约信息
     * @param parkingOrderVo 前端查询条件实体
     * @return 分页类
     */
    IPage<ResParkingOrderVO> getPageList(ParkingOrderVo parkingOrderVo);

    /**
     *  查找我的停车记录
     */
    IPage<ResParkingOrderVO> getMyReservationInfo(BasePageVo baseVo, String openId);

    /**
     * 预约订单进行中停车信息
     * @return
     */
    IPage<ResParkingOrderVO> findMyForReservation(BasePageVo basePageVo,String openId);

    /**
     * 预约订单已完成停车信息
     * @return
     */
    IPage<ResParkingOrderVO> findMyFinishReservation(BasePageVo basePageVo,String openId);

    /**
     * 删除订单
     * @param  id
     * @return boolean
     */
    boolean delete(String id);


    Boolean updates(UpdateOrderVo vo);


    /**
     * 订单确认进场
     * @param orderId
     * @return
     */
    ParkingOrder confirmApproach(String orderId);


    /**
     * 获取用户车位共享过的记录
     * @return
     */
    List<ResParkingOrderVO> iSharedRecords(String openId);

    /**
     * 取消预约订单
     * @param orderId
     * @return
     */
    boolean cancellationOrder(String orderId);


    /**
     * 根据订单号获取订单信息
     * @param orderId
     * @return
     */
    ParkingOrder getParkingOrder(String orderId);


    /**
     * 提交结算订单，返回订单信息
     * @param orderId 订单id
     * @return
     */
    ResParkingOrderVO submitSettlementOrder(String orderId);



    /**
     * 是否入场
     * @param orderId 订单id
     * @return
     */
    Boolean whetherAdmission(String orderId);

    /**
     * 获取订单信息 关联车位
     * @param orderId
     * @return
     */

    ResParkingOrderVO getOrderInfo(String orderId);


    /**
     * 是否有订单进行
     * @param openId
     * @return
     */
    List<ParkingOrder> ifThereSAnyOrder(String openId);


    /**
     * 查找用户进行中的订单
     * @param openId
     * @return
     */
    ParkingOrder ongoOrder(String openId);



    /**
     * 支付订单
     * @param orderId
     * @return
     */
    Boolean payment(String orderId,String openId);


    /**
     * 财务报表查询
     * @param reportConditionVo
     * @return
     */
   Map<String,Object> incomeStatement(ReportConditionVo reportConditionVo);


    /**
     * 订单报表查询
     * @param reportConditionVo
     * @return
     */
    Map<String,Object> orderReport(ReportConditionVo reportConditionVo);


    /**
     * 识别车牌确认入场
     * @param licensePlate
     * @return
     */
    ParkingOrder  identificationPlateIntoThe(LicensePlate licensePlate);

    /**
     * 识别车牌确认出场结算订单
     * @param licensePlate
     * @return
     */
    ResParkingOrderVO  orderConfirmationForSettlement(LicensePlate licensePlate);

    /**
     * 轮询订单超时
     */
    void orderTimeout();


}
