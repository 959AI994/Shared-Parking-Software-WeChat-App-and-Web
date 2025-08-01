package com.lbk.sharper.util;

import java.math.BigDecimal;

/**
 * @Author: wjx
 * @Date: 2023/5/26 21:53
 * @Description 计算两点地图坐标的距离
 * @Version 1.0
 */
public class LocationUtils {
    private static double EARTH_RADIUS = 6378.137;
//这行代码定义了地球的平均半径，用于计算两个经纬度之间的距离。
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
//这个方法将角度转换为弧度。在距离计算中，需要将经纬度的角度转换为弧度。
    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        //这个方法用于计算两个经纬度之间的距离。
        // 它接受四个参数：起始点的纬度 lat1、起始点的经度 lng1、目标点的纬度 lat2 和目标点的经度 lng2。
        // 根据经纬度的差值以及地球的半径，
        // 使用 Haversine 公式计算两点之间的距离。
        // 最后，使用 BigDecimal 进行精确计算和舍入，返回距离值。
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
//        s = s * 1000;
        BigDecimal res = new BigDecimal(s);
        return res.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
