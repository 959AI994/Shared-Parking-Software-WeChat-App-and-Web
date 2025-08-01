package com.lbk.sharper.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: WJX
 * @Date: 2023/6/10 8:10
 * @Description
 * @Version 1.0
 */
@Data
//ResParkingOrderVO 的类是一个数据传输对象（Data Transfer Object，DTO），
// 用于在不同层之间传递数据。它用于传递停车订单的信息，并包含了与订单相关的各种字段。
public class ResParkingOrderVO {
    @ApiModelProperty(value = "订单号")
    private String orderId;

    @ApiModelProperty(value = "停车场名称")
    private String villageName;

    @ApiModelProperty(value = "车牌号")
    private String carNumber;

    @ApiModelProperty(value = "车位号")
    private String parkingSpaceNumber;

    @ApiModelProperty(value = "车位编号")
    private Integer parkingSpaceId;

    @ApiModelProperty(value = "停车场地址")
    private String address;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;

    @ApiModelProperty(value = "进行中，已完成，取消")
    private String state;

    @ApiModelProperty(value = "支付状态")
    private String paymentStatus;

    @ApiModelProperty(value = "停车时长")
    private String parkingDuration;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "进场时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime approachTime;

    @ApiModelProperty(value = "出场时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime playingTime;

    @ApiModelProperty(value = "实际出场时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime actualPlayingTime;

    @ApiModelProperty(value = "支付金额")
    @TableField("paymentAmount")
    private Float paymentAmount;

    @ApiModelProperty(value = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "车位价格")
    private Float price;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

}
