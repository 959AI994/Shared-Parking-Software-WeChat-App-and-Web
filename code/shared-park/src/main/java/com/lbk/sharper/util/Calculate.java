package com.lbk.sharper.util;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * @Author: WJX
 * @Date: 2023/5/21 17:22
 * @Description  一些计算方法
 * @Version 1.0
 */
public class Calculate {

    /**
     * 分钟结算
     * @param minuteStr
     * @param price
     * @return
     */
    public static double CalculateMin( long minuteStr,Float price) {
        //判断停车时长小于10分钟
        if(minuteStr<10){
            return 3;
        }
        BigDecimal b1 = new BigDecimal(Double.valueOf(minuteStr));
        BigDecimal b2 = new BigDecimal(Double.valueOf(price/ 60.0));
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 小时结算
     * @param minuteStr
     * @param price
     * @return
     */
    public static double CalculateHour( long minuteStr,Float price) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(minuteStr));
        BigDecimal b2 = new BigDecimal(Double.valueOf(price));
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 金额相加
     * @param var1
     * @param var2
     * @return
     */
    public static double amountAccumulation( double var1,double var2) {
        BigDecimal b1 = new BigDecimal(var1);
        BigDecimal b2 = new BigDecimal(var2);
        return b1.add(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }



}
