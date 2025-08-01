package com.lbk.sharper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbk.sharper.common.BeanCopyUtils;
import com.lbk.sharper.common.BizState;
import com.lbk.sharper.common.CustomException;
import com.lbk.sharper.common.ResponseEnums;
import com.lbk.sharper.common.validator.Assert;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.entity.ParkingInfo;
import com.lbk.sharper.entity.ParkingOrder;
import com.lbk.sharper.entity.ParkingSpace;
import com.lbk.sharper.mapper.ParkingOrderMapper;
import com.lbk.sharper.mapper.ParkingSpaceMapper;
import com.lbk.sharper.res.ResOrderReportVo;
import com.lbk.sharper.res.ResParkingOrderVO;
import com.lbk.sharper.res.ResParkingSpaceVo;
import com.lbk.sharper.res.ResReportVo;
import com.lbk.sharper.service.INormalUserService;
import com.lbk.sharper.service.IParkingOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.service.IParkingSpaceService;
import com.lbk.sharper.util.Calculate;
import com.lbk.sharper.util.DateUtils;
import com.lbk.sharper.util.MapUtils;
import com.lbk.sharper.util.OrderCodeFactory;
import com.lbk.sharper.req.OrderFrom;
import com.lbk.sharper.vo.BaseVo;
import com.lbk.sharper.vo.LicensePlate;
import com.lbk.sharper.vo.ParkingOrderVo;
import com.lbk.sharper.vo.ReportConditionVo;
import com.lbk.sharper.webreq.UpdateOrderVo;
import com.lbk.sharper.wx.wxvo.BasePageVo;
//import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unbescape.css.CssIdentifierEscapeLevel;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 预约停车 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-6-15
 */
@Service
public class ParkingOrderServiceImpl extends ServiceImpl<ParkingOrderMapper, ParkingOrder> implements IParkingOrderService {
    @Autowired
    ParkingSpaceMapper parkingSpaceMapper;
    @Autowired
    IParkingSpaceService iParkingSpaceService;
    @Autowired
    INormalUserService iNormalUserService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String add(OrderFrom vo,String openId) {

        List<ResParkingSpaceVo> myPlea = iParkingSpaceService.getMyParkingSpaceList(openId);

        if(this.ifThereSAnyOrder(openId).size()>=1){
            throw new CustomException(407,"您有进行中的订单");
        }

        for (ResParkingSpaceVo resParkingSpaceVo : myPlea) {
            if (resParkingSpaceVo.getParkingSpaceId().equals(vo.getParkingSpaceId())){
                throw new CustomException("这是您的车位");
            }
        }

        ResParkingSpaceVo resSpaceVo = iParkingSpaceService.querySpaceServiceById(vo.getParkingSpaceId());
        if(resSpaceVo==null&&resSpaceVo.getIsTakeUp()!=0 && !resSpaceVo.getState().equals(BizState.inShar)){
            throw new CustomException("车位状态错误");
        }


//        if (vo.getPlayingTime().isAfter(resSpaceVo.getShareEndTime())||!vo.getPlayingTime().isAfter(resSpaceVo.getShareStartTime())){
//            throw new CustomException("不在业主共享时间");
//        }

        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setOrderId(OrderCodeFactory.getOrderCode(null));
        parkingOrder.setOpenId(openId);
        parkingOrder.setState(BizState.ongo);
        parkingOrder.setCreateTime(LocalDateTime.now());
        parkingOrder.setParkingSpaceId(vo.getParkingSpaceId());
//        parkingOrder.setPlayingTime(vo.getPlayingTime());

        //修改车位占用状态
        if (! iParkingSpaceService.openOccupancyState(vo.getParkingSpaceId())) {
            throw new CustomException(ResponseEnums.SERVER_ERROR);
        }

        //增加订单信息
        if (!this.save(parkingOrder)) {
            throw new CustomException(ResponseEnums.SERVER_ERROR);
        }

        return  parkingOrder.getOrderId();
    }

    @Override
    public IPage<ResParkingOrderVO> getPageList(ParkingOrderVo parkingOrderVo) {
        Page<ParkingOrderVo> page = new Page<>(parkingOrderVo.getCurrent(), parkingOrderVo.getPageSize());
        return baseMapper.getParkingReservationListPage(page,parkingOrderVo);
    }

    @Override
    public IPage<ResParkingOrderVO> getMyReservationInfo(BasePageVo baseVo, String openId) {
        Page<ParkingOrderVo> page = new Page<>(baseVo.getCurrent(), baseVo.getPageSize());
        return baseMapper.findMyReservation(page, openId);
    }

    @Override
    public IPage<ResParkingOrderVO> findMyForReservation(BasePageVo basePageVo,String openId) {
        Page<ParkingOrderVo> page = new Page<>(basePageVo.getCurrent(), basePageVo.getPageSize());
        return baseMapper.findMyForReservation(page, openId);
    }

    @Override
    public IPage<ResParkingOrderVO> findMyFinishReservation(BasePageVo basePageVo,String openId) {
        Page<ParkingOrderVo> page = new Page<>(basePageVo.getCurrent(), basePageVo.getPageSize());
        return baseMapper.findMyFinishReservation(page, openId);
    }

    @Override
    public boolean delete(String id) {
        return this.removeById(id);
    }

    @Override
    public Boolean updates(UpdateOrderVo vo) {
        ParkingOrder parkingOrder = BeanCopyUtils.copyProperties(vo, ParkingOrder::new);
        return this.updateById(parkingOrder);
    }

    @Override
    /*功能：
    这段代码的目的是更新指定订单的相关信息，包括将订单的 "parkingSpaceId" 字段更新为指定的 orderId，
    并设置订单的 "approachTime" 字段为当前时间。
    在更新过程中，如果出现错误，则抛出异常。最后，返回更新后的停车订单对象。
    * */
    //用于确认与提供的 orderId 相关的停车订单的操作或事件。
    //该代码使用了 MyBatis-Plus，这是一个增强版的 MyBatis 工具，简化了一些常见操作，如 CRUD 操作。
    public ParkingOrder confirmApproach(String orderId) {
        UpdateWrapper<ParkingOrder> updateWrapper = new UpdateWrapper<>();//这个对象用于构建 SQL 更新操作的条件。
        //updateWrapper.set("parkingSpaceId",orderId);//设置更新语句中的 "parkingSpaceId" 字段为指定的 orderId 值。
        //这个不对 ”id” : "123"
        //updateWrapper.eq("id","123");//设置查询条件，筛选出 id 字段值为 "123" 的记录进行更新。
        //this.update(updateWrapper);//执行更新操作，将满足条件的记录的 "parkingSpaceId" 字段更新为指定的 orderId 值。*/
        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setApproachTime(LocalDateTime.now());
        //设置其 "approachTime" 字段为当前时间，设置其 "orderId" 字段为指定的 orderId 值。
        parkingOrder.setOrderId(orderId);//设置查询条件，筛选出 "orderId" 字段值为指定的 orderId 的记录。
        updateWrapper.eq("orderId", orderId);
        if ( !this.updateById(parkingOrder)) {//更新失败，则抛出一个 CustomException 异常
            throw new CustomException(ResponseEnums.SERVER_ERROR);
        }
        return this.getParkingOrder(orderId);//获取并返回指定 orderId 的停车订单对象。
    }

    @Override
    public List<ResParkingOrderVO>iSharedRecords(String openId) {
        return baseMapper.iSharedRecords(openId);

    }

    @Override
    public boolean cancellationOrder(String orderId) {

        //获取订单信息
        ParkingOrder parkingOrder = this.getParkingOrder(orderId);

        if (parkingOrder==null) {
            throw new CustomException("订单错误");
        }

        if (parkingOrder.getApproachTime() != null) {
            throw new CustomException("该订单已进场，请刷新页面");
        }

        //删除订单并修改车位占用状态
        return this.removeById(orderId)&&iParkingSpaceService.closeToTakeUpState(parkingOrder.getParkingSpaceId());
    }

    @Override
    public ParkingOrder getParkingOrder(String orderId) {
        QueryWrapper<ParkingOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderId", orderId);
        return this.getOne(queryWrapper);
    }

    @Override
    public ResParkingOrderVO submitSettlementOrder(String orderId) {

        //获取当前时间
        LocalDateTime newDateTime = LocalDateTime.now();

        //查询次订单的数据
        ResParkingOrderVO resParkingOrderVO = this.getOrderInfo(orderId);
        //传回前端对象
        ResParkingOrderVO resParkingOrderVORes = new ResParkingOrderVO();
        BeanUtils.copyProperties(resParkingOrderVO, resParkingOrderVORes);
        //判断是否入场
        if(resParkingOrderVO.getApproachTime()==null){
            throw new CustomException("订单未确认入场");
        }

        //当订单已完成时直接返回
        if(resParkingOrderVO.getState().equals(BizState.complet)){
            return resParkingOrderVORes;
        }

        //计算停车时间和费用
        Map<String, Object> resMap = this.calculationParkingFees(resParkingOrderVO.getApproachTime(),newDateTime,resParkingOrderVO.getPrice());
        resParkingOrderVORes.setParkingDuration((String) resMap.get("resultStr"));
        resParkingOrderVORes.setPaymentAmount((Float) resMap.get("paymentAmount"));
        resParkingOrderVORes.setActualPlayingTime(newDateTime);

        //修改订单状态
        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setActualPlayingTime(newDateTime);
        parkingOrder.setOrderId(orderId);
        //parkingOrder.setState(BizState.complet);
        parkingOrder.setPaymentStatus(BizState.didNotPay);
        //写入订单金额
        parkingOrder.setPaymentAmount((Float) resMap.get("paymentAmount"));
        parkingOrder.setParkingDuration((String) resMap.get("resultStr"));
        if(!updateById(parkingOrder)){
            throw new CustomException(ResponseEnums.BODY_NOT_MATCH);
        }

        //关闭车位占用状态
        if(!iParkingSpaceService.closeToTakeUpState(resParkingOrderVO.getParkingSpaceId())){
            throw new CustomException("订单结束关闭车位占用状态错误");
        }
        return resParkingOrderVORes;
    }

    /**
     * 计算停车时间和结算金额
     * @param startTime 进场时间
     * @param actualPlayingTime 出场时间
     * @param price 业主价格
     * @return 停车时长和停车费用
     */
    private Map<String,Object> calculationParkingFees(LocalDateTime startTime,LocalDateTime actualPlayingTime,Float price){
        Duration duration = Duration.between(startTime, actualPlayingTime);
        //天
        long tian = duration.toDays();
        //小时
        long shi = duration.toHours();
        //相差的分钟数
        long minuteStr = duration.toMinutes();

        //相差的秒
        long second = duration.getSeconds();

        //拼接总停车时长
        String resultStr;
        //停车费用
        double paymentAmount=0;
        if (minuteStr != 0) {
            if (minuteStr/60 == 0) {
                resultStr = minuteStr%60+"分钟";
                paymentAmount = Calculate.CalculateMin(minuteStr,price);
            }else{
                if (minuteStr%60 == 0) {
                    resultStr = minuteStr/60+"小时";
                    Long hous = minuteStr/60;
                    paymentAmount = Calculate.CalculateHour(hous,price);
                }else{
                    resultStr = (minuteStr/60+"小时"+minuteStr%60+"分");
                    paymentAmount = Calculate.CalculateMin(minuteStr,price);
                }
            }
        }else{
            //秒的停车收费：停车十分钟内收费3元
            resultStr = second+"秒";
            paymentAmount = 3;
        }
        return new MapUtils().put("resultStr", resultStr).put("paymentAmount", (float) paymentAmount);
    }


    @Override
    public Boolean whetherAdmission(String orderId) {
        QueryWrapper<ParkingOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderId", orderId);
        ParkingOrder parkingOrder= this.getOne(queryWrapper);
        if (parkingOrder.getApproachTime()==null){
            return false;
        }
        return true;
    }

    @Override
    public ResParkingOrderVO getOrderInfo(String orderId) {
        return baseMapper.getOrderInfo(orderId);
    }

    @Override
    public List<ParkingOrder> ifThereSAnyOrder(String openId) {
        QueryWrapper<ParkingOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openId", openId);
        queryWrapper.eq("state", BizState.ongo);
        return  this.list(queryWrapper);
    }

    @Override
    public ParkingOrder ongoOrder(String openId) {
        QueryWrapper<ParkingOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openId", openId);
        queryWrapper.eq("state", BizState.ongo);
        return  this.getOne(queryWrapper);
    }

    @Override
    public Boolean payment(String orderId,String openId) {

        ParkingOrder res = this.getParkingOrder(orderId);

        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setOrderId(orderId);
        parkingOrder.setPaymentTime(LocalDateTime.now());
        parkingOrder.setState(BizState.complet);
        parkingOrder.setPaymentStatus(BizState.haveToPay);

        //获取停车用户信息
        NormalUser Parkingser = iNormalUserService.getUser(openId);
        if(Parkingser.getBalance()<res.getPaymentAmount()){
            throw  new  CustomException("余额不足");
        }

        //减去余额
        iNormalUserService.paymentBalance(openId, res.getPaymentAmount());


        //获取共享车位用户信息
        String userOpenId = iParkingSpaceService.getOpenIdBySpaceId(res.getParkingSpaceId());
        NormalUser user = iNormalUserService.getUser(userOpenId);

        NormalUser userUpdate = new NormalUser();
        userUpdate.setOpenId(userOpenId);
        //计算用户得到的订单金额
        userUpdate.setBalance(Calculate.amountAccumulation(user.getBalance(), Double.valueOf(res.getPaymentAmount())));
        //修改用户金额
        Boolean aBoolean= iNormalUserService.updateById(userUpdate);

        return this.updateById(parkingOrder);
    }

    @Override
    public Map<String, Object> incomeStatement(ReportConditionVo reportConditionVo) {
//财务报表统计
        List<ResReportVo> resReportVos = baseMapper.incomeStatement(reportConditionVo);
//调用 baseMapper 的 incomeStatement 方法进行财务报表统计，并将结果存储在 resReportVos 列表中。
        List<String> xData = new ArrayList<>();//创建一个空的字符串列表，用于存储 X 轴的数据。
        List<Double> yData = new ArrayList<>();//创建一个空的字符串列表，用于存储 Y轴的数据。
        for (int i = 1; i <= 12; i++) { //这是一个循环，从1到12遍历每个月份。
            Map<String, Object> isCunZai = this.isCunZai(i, resReportVos);

            if (Boolean.valueOf((Boolean) isCunZai.get("boolean"))){//判断某个月份的数据是否存在，并将结果存储在 isCunZai 中。
                xData.add(i+"月");
                String var = String.valueOf((Double) isCunZai.get("var"));
                yData.add(Double.valueOf(var));
            }else {
                xData.add(i+"月");
                yData.add((double) 0);
            }
        }
        //这行代码使用 MapUtils 创建一个包含 X 轴和 Y 轴数据的 Map，并将其作为方法的返回值返回。
        return new MapUtils().put("xData", xData).put("yData", yData);
    }

    @Override //为前端echart表格数据库请求数据
    public Map<String, Object> orderReport(ReportConditionVo reportConditionVo) {
        List<ResOrderReportVo> resReportVos = baseMapper.orderReport(reportConditionVo);

        List<String> xData = new ArrayList<>();
        List<Integer> yData = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            Map<String, Object> isCunZai = this.orderIsCunZai(i, resReportVos);

            if (Boolean.valueOf((Boolean) isCunZai.get("boolean"))){
                xData.add(i+"月");
                String var = String.valueOf( isCunZai.get("var"));
                yData.add(Integer.valueOf(var));
            }else {
                xData.add(i+"月");
                yData.add(0);
            }
        }
        return new MapUtils().put("xData", xData).put("yData", yData);

    }

    @Override
    public ParkingOrder identificationPlateIntoThe(LicensePlate licensePlate) {

        Assert.isBlank(licensePlate.getLicensePlateNumber(),"未检测到车牌号");

        QueryWrapper<NormalUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("carNumber", licensePlate.getLicensePlateNumber());
        NormalUser normalUser = iNormalUserService.getOne(queryWrapper);
        if (normalUser==null){
            throw new CustomException("当前车辆不是共享车！");
        }
        //获取真正进行中的订单
        ParkingOrder parkingOrder =this.ongoOrder(normalUser.getOpenId());
        if (parkingOrder==null){
            throw new CustomException("当前车辆不在共享订单内！");
        }
        //判断是否确认入场
        if (parkingOrder.getApproachTime() != null) {
            throw new CustomException("该订单以进场！");
        }
        //确认入场
        return this.confirmApproach(parkingOrder.getOrderId());
    }

    @Override
    public ResParkingOrderVO orderConfirmationForSettlement(LicensePlate licensePlate) {
        Assert.isBlank(licensePlate.getLicensePlateNumber(),"未检测到车牌号");
        QueryWrapper<NormalUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("carNumber", licensePlate.getLicensePlateNumber());
        NormalUser user = iNormalUserService.getOne(queryWrapper);
        if (user==null){
            throw new CustomException("当前车辆不是共享车！");
        }
        //获取真正进行中的订单
        ParkingOrder parkingOrder =this.ongoOrder(user.getOpenId());


        if (parkingOrder==null){
            throw new CustomException("当前车辆不在共享订单内！");
        }

        if (parkingOrder.getApproachTime() == null) {
            throw new CustomException("该订单还未入场！");
        }
        //判断是否结算，出场时间
        if (parkingOrder.getActualPlayingTime() != null) {
            throw new CustomException("该订单以出场结算！");
        }
        //确认出场，结算订单
        return this.submitSettlementOrder(parkingOrder.getOrderId());
    }

    /**
     * 检索这个月是否存在订单统计
     * @param i 月
     * @param resReportVos 数据
     * @return
     */
    public Map<String,Object> isCunZai(int i, List<ResReportVo> resReportVos){
        Map<String, Object> map = new HashMap<>();
        for (ResReportVo resReportVo : resReportVos) {
            if (resReportVo.getMonths() == i) {
                map.put("boolean", true);
                map.put("var", resReportVo.getOutcome());
                return map;
            }
        }
        map.put("boolean", false);
        map.put("var", 0);
        return map;
    }

    /**
     * 检索i当前月是否存在订单统计
     * @param i 月
     * @param resOrderReportVos 数据
     * @return
     */
    public Map<String,Object> orderIsCunZai(int i, List<ResOrderReportVo> resOrderReportVos){
        Map<String, Object> map = new HashMap<>();
        for (ResOrderReportVo resOrderReportVo : resOrderReportVos) {
            if (resOrderReportVo.getMonths() == i) {
                map.put("boolean", true);
                map.put("var", resOrderReportVo.getNumbers());
                return map;
            }
        }
        map.put("boolean", false);
        map.put("var", 0);
        return map;
    }

    @Override
    public void orderTimeout() {
        QueryWrapper<ParkingOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", BizState.ongo);

        List<ParkingOrder> parkingOrderList = this.list(queryWrapper);

        for (ParkingOrder parkingOrder : parkingOrderList) {

            if (parkingOrder.getApproachTime() == null) {
                if(parkingOrder.getCreateTime().plusMinutes(15).isBefore(LocalDateTime.now())){
                    this.cancellationOrder(parkingOrder.getOrderId());
                    System.out.println("》》》》》》》》》》》》取消的订单信息："+parkingOrder);
                }

            }

        }

    }
}
