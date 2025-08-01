package com.lbk.sharper.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lbk.sharper.entity.ParkingOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbk.sharper.res.ResOrderReportVo;
import com.lbk.sharper.res.ResParkingOrderVO;
import com.lbk.sharper.res.ResReportVo;
import com.lbk.sharper.vo.ParkingOrderVo;
import com.lbk.sharper.vo.ReportConditionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 停车订单 Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-5-17
 */
@Mapper
public interface ParkingOrderMapper extends BaseMapper<ParkingOrder> {


    /**
     * 管理员分页查询共享停车订单
     * @param
     */
    IPage<ResParkingOrderVO> getParkingReservationListPage(IPage<ParkingOrderVo> page,@Param("dto") ParkingOrderVo dto);


    /**
     * 我的预约停车信息
     * @param openId 微信用户ID
     * @return
     */
    IPage<ResParkingOrderVO> findMyReservation(IPage<ParkingOrderVo> page,String openId);

    /**
     * 预约订单进行中停车信息
     * @param openId 微信用户ID
     * @return
     */
    IPage<ResParkingOrderVO> findMyForReservation(IPage<ParkingOrderVo> page,String openId);

    /**
     * 预约订单已完成停车信息
     * @param openId 微信用户ID
     * @return
     */
    IPage<ResParkingOrderVO> findMyFinishReservation(IPage<ParkingOrderVo> page,String openId);


    /**
     * 获取订单信息
     * @param orderId
     * @return
     */
    ResParkingOrderVO getOrderInfo(String orderId);


    /**
     * 用户共享过车位的停放记录
     * @param openId
     * @return
     */
    List<ResParkingOrderVO> iSharedRecords(String openId);



    /**
     * 财务报表
     * @param vo
     * @return
     */
    List<ResReportVo> incomeStatement(@Param("vo") ReportConditionVo vo); //@Param注解，并为其指定一个字符串值（例如"vo"），可以确保在SQL查询中正确地引用该参数。

    /**
     * 订单报表
     * @param vo
     * @return
     */
    List<ResOrderReportVo> orderReport(@Param("vo") ReportConditionVo vo);


}
