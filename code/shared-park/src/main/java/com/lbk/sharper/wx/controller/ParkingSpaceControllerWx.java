package com.lbk.sharper.wx.controller;


import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.common.validator.ValidatorUtils;
import com.lbk.sharper.req.ParkingSpaceFrom;
import com.lbk.sharper.service.IParkingSpaceService;
import com.lbk.sharper.vo.ParkingSpaceVo;
import com.lbk.sharper.wx.annotation.Login;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 车位归属表 前端控制器
 * </p>
 *
 * @author wjx
 * @since 2023-5-15
 */
@RestController
@RequestMapping("/wx/parkingSpace")
public class ParkingSpaceControllerWx {
    @Autowired
    IParkingSpaceService iParkingSpaceService;

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

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ApiOperation(value="添加车位信息", httpMethod = "POST")
    public Result addRecord(ParkingSpaceVo parkingSpaceVo) {
        return ResultUtil.success(iParkingSpaceService.add(parkingSpaceVo));
    }


    @RequestMapping(value="/delete", method = RequestMethod.POST)
    @ApiOperation(value="删除车位信息", httpMethod = "POST")
    public Result delete(ParkingSpaceVo parkingSpaceVo) {
        return ResultUtil.success(iParkingSpaceService.delete(parkingSpaceVo));
    }

    @Login
    @RequestMapping(value="/getMyParkingSpace", method = RequestMethod.GET)
    @ApiOperation(value="查询我的车位信息", httpMethod = "GET")
    public Result
    getMyParkingSpace(@RequestAttribute("openId")String openId) {
        return ResultUtil.success(iParkingSpaceService.getMyParkingSpace(openId));
    }

    @Login
    @PostMapping("/turnSharing")
    @ApiOperation(value="开启车位共享", httpMethod = "POST")
    public Result turnSharing(@Validated ParkingSpaceFrom parkingSpaceFrom, @RequestAttribute("openId")String openId) {
        return ResultUtil.success(iParkingSpaceService.turnSharing(parkingSpaceFrom,openId));
    }

    @Login
    @RequestMapping(value="/closeSharing", method = RequestMethod.POST)
    @ApiOperation(value="关闭车位共享", httpMethod = "POST")
    public Result closeSharing( @RequestAttribute("openId")String openId) {
        return ResultUtil.success(iParkingSpaceService.closeSharing(openId));
    }
}
