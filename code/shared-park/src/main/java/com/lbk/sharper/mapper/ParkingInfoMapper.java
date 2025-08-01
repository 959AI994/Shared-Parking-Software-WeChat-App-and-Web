package com.lbk.sharper.mapper;

import com.lbk.sharper.entity.ParkingInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbk.sharper.res.RemainingSpaceVo;
import com.lbk.sharper.vo.ParkingInfoVo;
import com.lbk.sharper.wx.res.ParkingInfoVoWx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 小区停车场表 Mapper 接口
 * </p>
 *
 * @author WJX
 * @since 2023-5-27
 */
@Mapper
public interface ParkingInfoMapper extends BaseMapper<ParkingInfo> {

    /**
     * 获取停车场空余车位
     * @param dto 参数
     * @return
     */
    List<RemainingSpaceVo> findRemainingSpace(@Param("dto") ParkingInfoVo dto) ;


    List<ParkingInfoVoWx> getListMap() ;


}
