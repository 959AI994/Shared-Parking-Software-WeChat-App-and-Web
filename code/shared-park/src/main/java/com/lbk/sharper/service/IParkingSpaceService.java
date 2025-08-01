package com.lbk.sharper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lbk.sharper.entity.ParkingInfo;
import com.lbk.sharper.entity.ParkingOrder;
import com.lbk.sharper.entity.ParkingSpace;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbk.sharper.req.ParkingSpaceFrom;
import com.lbk.sharper.res.ResParkingSpaceVo;
import com.lbk.sharper.vo.ParkingSpaceVo;
import com.lbk.sharper.webreq.SpaceWebVo;

import java.util.List;

/**
 * <p>
 * 车位归属表 服务类
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
public interface IParkingSpaceService extends IService<ParkingSpace> {
    /**
     * 分页查询小区停车场信息列表
     * @param  parkingSpaceVo
     * @return IPage
     */
    IPage<ResParkingSpaceVo> getPageList(ParkingSpaceVo parkingSpaceVo);


    /**
     * 查询全部小区停车场信息列表
     * @return List
     */
    List<ParkingSpace> getList();

    /**
     * 根据停车场ID找停车位列表
     * @param id
     * @return List
     */
    List<ResParkingSpaceVo> getSpaceServiceByIdList(int id);


    /**
     * 根据车位编查询车位信息
     * @param id
     * @return ResParkingSpaceVo
     */
    ResParkingSpaceVo querySpaceServiceById(int id);


    /**
     * 增加车位信息
     * @param  parkingSpace
     * @return boolean
     */
    boolean add(ParkingSpace parkingSpace);


    /**
     * 获取车位信息
     * @param parkingSpaceId
     * @return
     */
    ParkingSpace getParkingSpace(String parkingSpaceId);

    /**
     * 修改小区停车场信息
     * @param  parkingInfoVo
     * @return boolean
     */
    boolean updates(SpaceWebVo parkingInfoVo);

    /**
     * 删除小区停车场信息
     * @param  parkingInfoVo
     * @return boolean
     */
    boolean delete(ParkingSpaceVo parkingInfoVo);

    /**
     * 查询我的车位信息
     * @param openId
     * @return
     */
    ResParkingSpaceVo getMyParkingSpace(String openId);

    /**
     * 查询我的车位信息
     * @param openId
     * @return
     */
    List<ResParkingSpaceVo> getMyParkingSpaceList(String openId);

    /**
     * 业主开启共享车位
     * @param parkingSpaceFrom
     * @return
     */
    boolean turnSharing(ParkingSpaceFrom parkingSpaceFrom,String openId);

    /**
     * 业主关闭共享车位
     * @return
     */
    boolean closeSharing(String openId);


    /**
     * 关闭占用状态
     * @param parkingSpaceId 车位归属编号
     * @return
     */
    boolean closeToTakeUpState(Integer parkingSpaceId);


    /**
     * 开启占用状态
     */
    Boolean openOccupancyState(Integer parkingSpaceId);



    /**
     * 检查车位是否超过共享时段
     */
    void checkOff();


    /**
     * 关闭车位共享
     * @param parkingSpaceId
     * @return
     */
    boolean closeParkingSpace(Integer parkingSpaceId);


    /**
     * 根据车位编号查找归属用户openId
     * @param parkingSpaceId
     * @return
     */
    String getOpenIdBySpaceId(Integer parkingSpaceId);


}
