package com.lbk.sharper.wx.controller;


import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.req.ParkingInfoFrom;
import com.lbk.sharper.service.IParkingInfoService;
import com.lbk.sharper.vo.ParkingInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 小区停车场 前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-5-29
 */
@RestController
@RequestMapping("/wx/parkingInfo")
public class ParkingInfoControllerWx {
    @Autowired
    IParkingInfoService IParkingInfoService;


    @RequestMapping(value="/getNearbyList",method = RequestMethod.POST)
    @ApiOperation(value="获取附近小区停车场信息",httpMethod = "POST")
    public Result getNearbyList(ParkingInfoVo parkingInfoVo) {
        return ResultUtil.success(IParkingInfoService.getNearbyList(parkingInfoVo));
    }

    @RequestMapping(value="/getList",method = RequestMethod.GET)
    @ApiOperation(value="获取全部小区停车场信息",httpMethod = "GET")
    public Result getList() {
        return ResultUtil.success(IParkingInfoService.getList());
    }

    @RequestMapping(value="/getListMap",method = RequestMethod.GET)
    @ApiOperation(value="获取地图上的标点小区信息和车位数",httpMethod = "GET")
    public Result getListMap() {
        return ResultUtil.success(IParkingInfoService.getListMap());
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ApiOperation(value="添加小区停车场信息", httpMethod = "POST")
    public Result addRecord(ParkingInfoFrom parkingInfoFrom) {
        return ResultUtil.success(IParkingInfoService.add(parkingInfoFrom));
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ApiOperation(value="修改小区停车场信息", httpMethod = "POST")
    public Result update(ParkingInfoVo parkingInfoVo) {
        return ResultUtil.success(IParkingInfoService.updates(parkingInfoVo));
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    @ApiOperation(value="删除小区停车场信息", httpMethod = "POST")
    public Result delete(ParkingInfoVo parkingInfoVo) {
        return ResultUtil.success(IParkingInfoService.delete(parkingInfoVo));
    }

    @RequestMapping(value="/pickerInfo", method = RequestMethod.GET)
    @ApiOperation(value="查询停车场列表——选择器", httpMethod = "GET")
    public Result pickerInfo() {
        return ResultUtil.success(IParkingInfoService.pickerInfo());
    }

}
