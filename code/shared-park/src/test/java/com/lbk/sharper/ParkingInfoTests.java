package com.lbk.sharper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbk.sharper.common.BeanCopyUtils;
import com.lbk.sharper.common.BizState;
import com.lbk.sharper.entity.ParkingInfo;
import com.lbk.sharper.mapper.ParkingInfoMapper;
import com.lbk.sharper.req.ParkingInfoFrom;
import com.lbk.sharper.res.RemainingSpaceVo;
import com.lbk.sharper.util.LocationUtils;
import com.lbk.sharper.util.PickerParkingInfo;
import com.lbk.sharper.vo.ParkingInfoVo;
import com.lbk.sharper.wx.res.ParkingInfoVoWx;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingInfoTests {

    ParkingInfoMapper parkingInfoMapper;


    @Test
    public IPage<ParkingInfo> getPageList(ParkingInfoVo parkingInfoVo) {
        IPage<ParkingInfo> page = new Page<>(parkingInfoVo.getCurrent(), parkingInfoVo.getPageSize());
        QueryWrapper<ParkingInfo> queryWrapper = new QueryWrapper<>();

        if (parkingInfoVo.getParkingInfoId()!=null){
            queryWrapper.eq("parkingInfoId",parkingInfoVo.getParkingInfoId());
        }
        if (!StringUtils.isEmpty(parkingInfoVo.getVillageName())){
            queryWrapper.eq("villageName",parkingInfoVo.getVillageName());
        }
        if (!StringUtils.isEmpty(parkingInfoVo.getAddress())){
            queryWrapper.like("address",parkingInfoVo.getAddress());
        }
        if (!StringUtils.isEmpty(parkingInfoVo.getState())){
            queryWrapper.eq("state",parkingInfoVo.getState());
        }
        if (!StringUtils.isEmpty(parkingInfoVo.getPhone())){
            queryWrapper.eq("phone",parkingInfoVo.getPhone());
        }
        return this.page(page, queryWrapper);
    }

    @Test
    public List<RemainingSpaceVo> getNearbyList(ParkingInfoVo parkingInfoVo) {
        List<RemainingSpaceVo> remainingSpaceVoListVo = new ArrayList<>();
        ParkingInfoMapper baseMapper = null;
        List<RemainingSpaceVo> remainingSpaceVoList= baseMapper.findRemainingSpace(parkingInfoVo);

        for ( RemainingSpaceVo ite:remainingSpaceVoList) {
            double distance =(LocationUtils.getDistance(Double.valueOf(parkingInfoVo.getLongitude()), Double.valueOf(parkingInfoVo.getLatitude()),
                    Double.valueOf(ite.getLongitude()), Double.valueOf(ite.getLatitude()))) ;
            if (5>distance){
                ite.setDistance(distance);
                remainingSpaceVoListVo.add(ite);
            }
        }
        return remainingSpaceVoListVo;
    }

    @Test
    public List<ParkingInfo> getList() {


        return this.list();
    }

    @Test
    public List<ParkingInfoVoWx> getListMap() {
        //1、获取车位信息
        ParkingInfoTests baseMapper = new ParkingInfoTests();
        List<ParkingInfoVoWx> parkingInfoVoWxList = baseMapper.getListMap();
        //2、根据parkinfo id 进行分组、
        Map<Integer, List<ParkingInfoVoWx>> jk = parkingInfoVoWxList.stream().collect(Collectors.groupingBy(ParkingInfoVoWx::getParkingInfoId));

        List<ParkingInfoVoWx> resList = new ArrayList<>();
        for (Integer s : jk.keySet()) {
            List<ParkingInfoVoWx> value = jk.get(s);
            int count = 0;
            ParkingInfoVoWx park = new ParkingInfoVoWx();
            for (ParkingInfoVoWx parkingInfoVoWx : value) {
                park = parkingInfoVoWx;
                if (parkingInfoVoWx.getIsTakeUp() == 0&&parkingInfoVoWx.getState().equals(BizState.inShar)) {
                    //如果是相同的地区 停车位数据自动加一
                    count++;
                }
            }
            //记录同一个地区有几个停车位
            park.setSpaceNumber(count);
            resList.add(park);
        }

        return resList;
    }

    /**
     * 该方法的目的是从数据库中查询 ParkingInfo 数据表的部分列，并将查询结果转换为 PickerParkingInfo 对象的列表返回。
     * @return
     */
    @Test
    public List<PickerParkingInfo> pickerInfo() {
        QueryWrapper<ParkingInfo> queryWrapper = new QueryWrapper<>();//构建查询条件
        queryWrapper.select("parkingInfoId","villageName");//指定查询结果中需要返回的列，即 parkingInfoId 和 villageName。
        List<PickerParkingInfo> res = new ArrayList<>();//对象 res，用于存储查询结果。
        //List<ParkingInfo> parkingInfoList = this.list(queryWrapper); //

        ParkingInfo[] parkingInfoList = new ParkingInfo[0];
        for (ParkingInfo item:  parkingInfoList) {
            PickerParkingInfo pic = new PickerParkingInfo();
            pic.setLabel(item.getVillageName()); //设置为 pic 的标签（label）。
            pic.setValue(item.getParkingInfoId());//设置为 pic 的值（value）。
            res.add(pic);
        }

        return res;//返回 res，即包含了查询结果的 List<PickerParkingInfo>。
    }
    private List<ParkingInfo> list() {
        return null;
    }
    private IPage<ParkingInfo> page(IPage<ParkingInfo> page, QueryWrapper<ParkingInfo> queryWrapper) {
        return null;
    }
}
