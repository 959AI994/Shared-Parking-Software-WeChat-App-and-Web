package com.lbk.sharper.controller;

import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.util.GouldUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 直接把高德地图工具类拿过来直接用
 * @Author: WJX
 * @Date: 2023/5/27 11:21
 * @Description 获取地图经纬度
 * @Version 1.0
 */

@RestController
@RequestMapping("/point")//注解将控制器的根路径设置为"/point"，表示处理以"/point"开头的请求。
public class PointController {
    @Autowired
    private GouldUtil gouldUtil;
    public static final Logger log = LoggerFactory.getLogger(PointController.class);
    //创建一个名为 log 的静态日志记录器。
    @PostMapping("/getAddress")//将 getAddress 方法映射到"/getAddress"的POST请求。
    @ApiOperation(value="根据经纬度获取地址", httpMethod = "POST")
    public Result getAddress(String longitude, String lat) {
        try {
            System.out.println(longitude);
            System.out.println(lat);
            String address = gouldUtil.getAMapByLngAndLat(longitude, lat);
            //调用 gouldUtil.getAMapByLngAndLat(longitude, lat) 方法来获取对应的地址，
            // 并将地址封装到表示成功的 Result 对象中返回。
            return ResultUtil.success(address);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    /**
     * 根据地址获取经纬度
     * @param address
     * @return
     */
    @GetMapping("/getLonLat")
    @ApiOperation(value="根据地址获取经纬度", httpMethod = "GET")
    public Result getLonLat(String address) {
        try {//获取对应的经纬度，并将经纬度封装到表示成功的 Result 对象中返回。
            Map<String,String> result = gouldUtil.getLonLat(address);
            return ResultUtil.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @GetMapping("/getDistanceByAddress/{start}/{end}")
    @ApiOperation(value="通过地址算出两个地址之间的距离", httpMethod = "GET")
    public Result getDistanceByAddress(@PathVariable String start,@PathVariable String end){
        return ResultUtil.success(gouldUtil.getDistanceByAddress(start,end));
        //调用 gouldUtil.getDistanceByAddress(start, end) 方法来计算两个地址之间的距离，
        // 并将结果封装到表示成功的 Result 对象中返回。
    }
}
