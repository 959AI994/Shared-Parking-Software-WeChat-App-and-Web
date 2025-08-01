package com.lbk.sharper.res;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: WJX
 * @Date: 2023/6/1 23:14
 * @Description
 * @Version 1.0
 */
// RemainingSpaceVo 的类是一个数据传输对象（Data Transfer Object，DTO），
// 用于在不同层之间传递数据。它用于传递停车场的剩余车位信息，并包含了一些与停车场相关的字段。
@Data
public class RemainingSpaceVo {
    @ApiModelProperty(value = "停车场编号")
    private Integer parkingInfoId;

    @ApiModelProperty(value = "小区名称")
    private String villageName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "剩余车位")
    private Integer remainingSpace;

    @ApiModelProperty(value = "距离")
    private double distance;
}
