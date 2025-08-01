package com.lbk.sharper.controller;


import com.lbk.sharper.common.BeanCopyUtils;
import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.req.ParkingSpaceFrom;
import com.lbk.sharper.service.IParkingSpaceService;
import com.lbk.sharper.vo.*;
import com.lbk.sharper.webreq.SpaceWebVo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 车位归属表 前端控制器
 * </p>
 *
 * @author WJX
 * @since 2023-5-25
 */
@RestController
@RequestMapping("/parkingSpace")
public class ParkingSpaceController {
    @Autowired
    IParkingSpaceService iParkingSpaceService;

    @RequestMapping(value="/getPageList",method = RequestMethod.POST)
    @ApiOperation(value="获取车位分页信息",httpMethod = "POST")
    public Result getPageList(ParkingSpaceVo parkingSpaceVo) {
        return ResultUtil.success(iParkingSpaceService.getPageList(parkingSpaceVo));
    }

    @RequestMapping(value="/getList",method = RequestMethod.GET)
    @ApiOperation(value="获取全部车位信息",httpMethod = "GET")
    public Result getList() {
        return ResultUtil.success(iParkingSpaceService.getList());
    }

    @RequestMapping(value="/getSpaceServiceByIdList",method = RequestMethod.GET)
    @ApiOperation(value="获取对应ID停车场空闲车位列表",httpMethod = "GET")
    public Result getSpaceServiceByIdList( int id) {
        return ResultUtil.success(iParkingSpaceService.getSpaceServiceByIdList(id));
    }

    @RequestMapping(value="/getSpaceServiceById",method = RequestMethod.GET)
    @ApiOperation(value="获取对应ID停车场车位",httpMethod = "GET")
    public Result getSpaceServiceById( int id) {
        return ResultUtil.success(iParkingSpaceService.querySpaceServiceById(id));
    }

//    @RequestMapping(value="/getParkingSpace",method = RequestMethod.GET)
//    @ApiOperation(value="获取车位信息",httpMethod = "GET")
//    public Result getParkingSpace( Integer  id) {
//        return ResultUtil.success(iParkingSpaceService.querySpaceServiceById(id));
//    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ApiOperation(value="添加车位信息", httpMethod = "POST")
    public Result addRecord(ParkingSpaceVo parkingSpaceVo) {
        return ResultUtil.success(iParkingSpaceService.add(parkingSpaceVo));
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ApiOperation(value="修改车位信息", httpMethod = "POST")
    public Result update(SpaceWebVo vo) {
        return ResultUtil.success(iParkingSpaceService.updates(vo));
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    @ApiOperation(value="删除车位信息", httpMethod = "POST")
    public Result delete(ParkingSpaceVo parkingSpaceVo) {
        return ResultUtil.success(iParkingSpaceService.delete(parkingSpaceVo));
    }

    @RequestMapping(value="/getMyParkingSpace", method = RequestMethod.GET)
    @ApiOperation(value="查询我的车位信息", httpMethod = "GET")
    public Result getMyParkingSpace() {
        NormalUser user = (NormalUser) SecurityUtils.getSubject().getPrincipal();
        return ResultUtil.success(iParkingSpaceService.getMyParkingSpace(user.getOpenId()));
    }

    @GetMapping(value="/count")
    @ApiOperation(value="获取总车位数",httpMethod = "GET")
    public Result count() {
        return ResultUtil.success(iParkingSpaceService.count());
    }


}
