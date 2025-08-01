package com.lbk.sharper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lbk.sharper.entity.ParkingInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbk.sharper.req.ParkingInfoFrom;
import com.lbk.sharper.res.RemainingSpaceVo;
import com.lbk.sharper.util.PickerParkingInfo;
import com.lbk.sharper.vo.ParkingInfoVo;
import com.lbk.sharper.wx.res.ParkingInfoVoWx;

import java.util.List;

/**
 * <p>
 * 小区停车场表 服务类
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
public interface IParkingInfoService extends IService<ParkingInfo> {
    /**
     * 分页查询小区停车场信息列表
     * @param  parkingInfoVo
     * @return IPage
     */
    IPage<ParkingInfo> getPageList(ParkingInfoVo parkingInfoVo);

    /**
     * 获取定位附近小区停车场信息列表
     * @param  parkingInfoVo
     * @return List
     */
    List<RemainingSpaceVo> getNearbyList(ParkingInfoVo parkingInfoVo);


    /**
     * 获取所有停车场信息列表
     * @return List
     */
    List<ParkingInfo> getList();


    /**
     * 获取所有停车场信息列表和地图上的车位数
     * @return List
     */
    List<ParkingInfoVoWx> getListMap();

    /**
     * 修改小区停车场信息
     * @param  parkingInfoFrom
     * @return boolean
     */
    boolean add(ParkingInfoFrom parkingInfoFrom);

    /**
     * 修改小区停车场信息
     * @param  parkingInfoVo
     * @return boolean
     */
    boolean updates(ParkingInfoVo parkingInfoVo);

    /**
     * 删除小区停车场信息
     * @param  parkingInfoVo
     * @return boolean
     */
    boolean delete(ParkingInfoVo parkingInfoVo);


    ParkingInfo getParkingInfo(String name);

    /**
     * 查询停车场列表——选择器
     * @return
     */
    List<PickerParkingInfo> pickerInfo();

}
