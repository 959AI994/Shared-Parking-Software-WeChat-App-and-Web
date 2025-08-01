package com.lbk.sharper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbk.sharper.common.BeanCopyUtils;
import com.lbk.sharper.common.BizState;
import com.lbk.sharper.common.CustomException;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.entity.ParkingInfo;
import com.lbk.sharper.entity.ParkingOrder;
import com.lbk.sharper.entity.ParkingSpace;
import com.lbk.sharper.req.ParkingSpaceFrom;
import com.lbk.sharper.mapper.ParkingSpaceMapper;
import com.lbk.sharper.res.ResParkingSpaceVo;
import com.lbk.sharper.service.IParkingSpaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.vo.ParkingSpaceVo;
import com.lbk.sharper.webreq.SpaceWebVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 车位归属表 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
@Service
public class ParkingSpaceServiceImpl extends ServiceImpl<ParkingSpaceMapper, ParkingSpace> implements IParkingSpaceService {
    @Autowired
    ParkingSpaceMapper parkingSpaceMapper;

    @Override
    public IPage<ResParkingSpaceVo> getPageList(ParkingSpaceVo parkingSpaceVo) {
        Page<ParkingSpaceVo> page = new Page<>(parkingSpaceVo.getCurrent(), parkingSpaceVo.getPageSize());
        return parkingSpaceMapper.getParkingSpaceListPage(page, parkingSpaceVo);
    }

    @Override
    public List<ParkingSpace> getList() {
        return null;
    }

    @Override
    public List<ResParkingSpaceVo> getSpaceServiceByIdList(int id) {
//        QueryWrapper<ParkingSpace> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parkingInfoId", id);
        return parkingSpaceMapper.findSpaceServiceByIdList(id);
    }

    @Override
    public ResParkingSpaceVo querySpaceServiceById(int id) {
        return parkingSpaceMapper.findSpaceServiceById(id);
    }

    @Override
    public boolean add(ParkingSpace parkingSpace) {
        return this.save(parkingSpace);
    }

    @Override
    public ParkingSpace getParkingSpace(String parkingSpaceId) {

        return this.getById(parkingSpaceId);
    }

    @Override
    public boolean updates(SpaceWebVo parkingInfoVo) {

        ParkingSpace parkingSpaces = this.getById(parkingInfoVo.getParkingSpaceId());
        if (parkingSpaces.getIsTakeUp()==1){
            throw new CustomException("该车位在共享停放中，不能修改");
        }

        if (parkingInfoVo.getState().equals(BizState.inShar)) {
            if (LocalDateTime.now().isAfter(parkingInfoVo.getShareEndTime())) {
                throw new CustomException("结束时间不能小于当前时间");
            }
        }
        ParkingSpace parkingSpace = BeanCopyUtils.copyProperties(parkingInfoVo, ParkingSpace::new);
        return this.updateById(parkingSpace);
    }

    @Override
    public boolean delete(ParkingSpaceVo parkingInfoVo) {
        return false;
    }

    @Override
    public ResParkingSpaceVo getMyParkingSpace(String openId) {
        return parkingSpaceMapper.getMyParkingSpace(openId);
    }

    @Override
    public List<ResParkingSpaceVo> getMyParkingSpaceList(String openId) {
        return parkingSpaceMapper.getMyParkingSpaceList(openId);
    }

    @Override
    public boolean turnSharing(ParkingSpaceFrom parkingSpaceFrom,String openId) {

        //查找我的车位信息
        ResParkingSpaceVo resParkingSpaceVo = this.getMyParkingSpace(openId);

        UpdateWrapper<ParkingSpace> updateWrapper = new UpdateWrapper<>();
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setParkingSpaceId(resParkingSpaceVo.getParkingSpaceId());
        LocalDateTime nowTime = LocalDateTime.now();

        if (parkingSpaceFrom.getShareEndTime().isBefore(parkingSpaceFrom.getShareStartTime())) {
            throw new CustomException("开始时间不能大于结束时间");
        } else if (parkingSpaceFrom.getShareStartTime().equals(parkingSpaceFrom.getShareEndTime())) {
            throw new CustomException("开始时间不能等于结束时间");
        }

        parkingSpace.setShareStartTime(parkingSpaceFrom.getShareStartTime());
        parkingSpace.setShareEndTime(parkingSpaceFrom.getShareEndTime());
        parkingSpace.setPrice((float) parkingSpaceFrom.getPrice());
        parkingSpace.setIsTakeUp(0);
        parkingSpace.setState(BizState.inShar);
        updateWrapper.eq("parkingSpaceId", resParkingSpaceVo.getParkingSpaceId());
        return this.updateById(parkingSpace);

    }

    @Override
    public boolean closeSharing(String openId) {
        //查找我的车位状态信息
        ResParkingSpaceVo resParkingSpaceVo = this.getMyParkingSpace(openId);

        if (resParkingSpaceVo.getIsTakeUp() == 0) {
            UpdateWrapper<ParkingSpace> updateWrapper = new UpdateWrapper<>();
            ParkingSpace parkingSpace = new ParkingSpace();
            parkingSpace.setParkingSpaceId(resParkingSpaceVo.getParkingSpaceId());
            parkingSpace.setState(BizState.notShar);
            parkingSpace.setIsTakeUp(0);
            updateWrapper.eq("parkingSpaceId", resParkingSpaceVo.getParkingSpaceId());
            return this.updateById(parkingSpace);
        }

        return false;

    }

    @Override
    public boolean closeToTakeUpState(Integer parkingSpaceId) {

        ParkingSpace parkingSpaceData = this.getOne(new QueryWrapper<ParkingSpace>().eq("parkingSpaceId", parkingSpaceId));
        UpdateWrapper<ParkingSpace> updateWrapper = new UpdateWrapper<>();
        ParkingSpace parkingSpace = new ParkingSpace();

        if (!parkingSpaceData.getShareEndTime().isAfter(LocalDateTime.now())) {
            parkingSpace.setState(BizState.notShar);
        }

        parkingSpace.setParkingSpaceId(parkingSpaceId);
        parkingSpace.setIsTakeUp(0);
        updateWrapper.eq("parkingSpaceId", parkingSpaceId);
        return this.updateById(parkingSpace);
    }

    @Override
    public Boolean openOccupancyState(Integer parkingSpaceId) {
        return baseMapper.updateParkingReservationState(parkingSpaceId);
    }

    @Override
    public void checkOff() {

        QueryWrapper<ParkingSpace> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", BizState.inShar);
        queryWrapper.eq("isTakeUp", 0);
        List<ParkingSpace> parkingSpaceList = baseMapper.checkOff();
        for (ParkingSpace parkingSpace : parkingSpaceList) {
            this.closeParkingSpace(parkingSpace.getParkingSpaceId());
        }
//        closeSharing
    }

    @Override
    public boolean closeParkingSpace(Integer parkingSpaceId) {
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setParkingSpaceId(parkingSpaceId);
        parkingSpace.setState(BizState.notShar);
        parkingSpace.setIsTakeUp(0);
        return this.updateById(parkingSpace);
    }

    @Override
    public String getOpenIdBySpaceId(Integer parkingSpaceId) {
        return baseMapper.getOpenIdBySpaceId(parkingSpaceId);
    }


}
