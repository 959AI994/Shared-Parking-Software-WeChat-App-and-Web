package com.lbk.sharper.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @Author: wjx
 * @Date: 2023/5/22 12:24
 * @Description 接受订单列表的查询参数
 * @Version 1.0
 */
@Data
public class ParkingOrderVo extends BaseVo{

    @ApiModelProperty(value = "订单号")
    private String orderId;

    @ApiModelProperty(value = "停车场名称")
    private String villageName;

    @ApiModelProperty(value = "车牌号")
    private String carNumber;

    @ApiModelProperty(value = "车位号")
    private String parkingSpaceNumber;

    @ApiModelProperty(value = "进行中，已完成，取消")
    private String state;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "电话")
    private String phone;



}
