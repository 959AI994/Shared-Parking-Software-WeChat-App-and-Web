package com.lbk.sharper.task;

import com.lbk.sharper.service.IParkingOrderService;
import com.lbk.sharper.service.IParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: wjx
 * @Date: 2023/6/3 22:09
 * @Description 开启定时任务的注解
 * @Version 1.0
 */
@Component
@EnableScheduling
public class TestTask {
    @Autowired
    IParkingOrderService orderService;
    @Autowired
    IParkingSpaceService parkingSpaceService;

//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 */1 * * * ?")
    public void job1() {
//        orderService.orderTimeout();
//        System.out.println("定时任务1_检查订单是否超时" + new Date());
//
//        parkingSpaceService.checkOff();
//        System.out.println("定时任务2_检查车位是否超过共享时段" + new Date());

    }


}
