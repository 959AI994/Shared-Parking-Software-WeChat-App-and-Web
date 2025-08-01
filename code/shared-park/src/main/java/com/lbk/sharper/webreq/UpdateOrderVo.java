package com.lbk.sharper.webreq;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Author: wjx
 * @Date: 2023/5/24 1:25
 * @Description
 * @Version 1.0
 */
@Data
public class UpdateOrderVo {
    @ApiModelProperty(value = "停车订单号")
    private String orderId;

    @ApiModelProperty(value = "进行中，已完成，取消")
    private String state;

    @ApiModelProperty(value = "停车时长")
    private String parkingDuration;

    @ApiModelProperty(value = "支付状态")
    private String paymentStatus;

    @ApiModelProperty(value = "进场时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime approachTime;

    @ApiModelProperty(value = "实际出场时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime actualPlayingTime;

    @ApiModelProperty(value = "支付金额")
    private Float paymentAmount;


}
