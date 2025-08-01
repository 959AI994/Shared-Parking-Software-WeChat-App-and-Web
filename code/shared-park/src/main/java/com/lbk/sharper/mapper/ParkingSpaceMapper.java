package com.lbk.sharper.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lbk.sharper.entity.ParkingSpace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbk.sharper.res.ResParkingSpaceVo;
import com.lbk.sharper.vo.ParkingSpaceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 车位归属表 Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-5-19
 */
@Mapper
public interface ParkingSpaceMapper extends BaseMapper<ParkingSpace> {

    List<ResParkingSpaceVo> findSpaceServiceByIdList(int id);


    /**
     * 修改车位占用状态
     * @param id
     * @return
     */
    Boolean updateParkingReservationState(Integer id);


    /**
     * 根据车位编查询车位信息
     * @param id
     * @return
     */
    ResParkingSpaceVo findSpaceServiceById(int id);

    /**
     * web分页查询详细车位信息
     */
//    List<ResParkingSpaceVo> getParkingSpaceListPage(int id);
    IPage<ResParkingSpaceVo> getParkingSpaceListPage(IPage<ParkingSpaceVo> page, @Param("dto") ParkingSpaceVo dto);

    /**
     * 查询我的车位信息
     * @param openID
     * @return
     */
    ResParkingSpaceVo getMyParkingSpace(String openID);


    /**
     * 查询我的全部车位信息
     * @param openID
     * @return
     */
    List<ResParkingSpaceVo> getMyParkingSpaceList(String openID);


    /**
     * 检查车位是否超过共享时段
     * @return
     */
    List<ParkingSpace> checkOff();


    /**
     * 根据车位编号查找归属用户openId
     * @param parkingSpaceId
     * @return
     */
    String getOpenIdBySpaceId(@Param("parkingSpaceId")  Integer parkingSpaceId);


}
