package com.lbk.sharper.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.entity.ParkingInfo;
import com.lbk.sharper.req.ParkingInfoFrom;
import com.lbk.sharper.service.IParkingInfoService;
import com.lbk.sharper.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 小区停车场 前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
@RestController
@RequestMapping("/parkingInfo")
public class ParkingInfoController {
    @Autowired
    IParkingInfoService IParkingInfoService;

    @RequestMapping(value="/getPageList",method = RequestMethod.POST)
    @ApiOperation(value="获取小区停车场分页信息",httpMethod = "POST")
    public Result getPageList( ParkingInfoVo parkingInfoVo) {
        return ResultUtil.success(IParkingInfoService.getPageList(parkingInfoVo));
    }

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

    /**
     * *调用了 IParkingInfoService（停车场信息服务）的 getOne 方法，
     * 通过 QueryWrapper 查询条件构造器，以 "parkingInfoId" 字段等于传入的 id 参数进行查询。
     * 然后，通过 ResultUtil.success 方法将查询结果封装成一个成功的返回结果。
     * @param id
     * @return
     */
    @RequestMapping(value="/getParkingInfo",method = RequestMethod.GET)
    @ApiOperation(value="根据id查询小区停车场信息",httpMethod = "GET")
    public Result getParkingInfo(Integer id) {
        return ResultUtil.success(IParkingInfoService.getOne(new QueryWrapper<ParkingInfo>().eq("parkingInfoId",id)));
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ApiOperation(value="添加小区停车场信息", httpMethod = "POST")
    public Result addRecord(@Validated  ParkingInfoFrom parkingInfoFrom) {
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
