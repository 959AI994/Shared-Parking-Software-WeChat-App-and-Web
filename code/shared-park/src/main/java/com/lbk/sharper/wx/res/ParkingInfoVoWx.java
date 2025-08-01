package com.lbk.sharper.wx.res;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: WJX
 * @Date: 2023/5/20 19:09
 * @Description  返回地图页面vo
 * @Version 1.0
 */
@Data
public class ParkingInfoVoWx {

    @ApiModelProperty(value = "停车场编号")
    private Integer parkingInfoId;

    @ApiModelProperty(value = "小区名称")
    private String villageName;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "共享中，未共享，禁用")
    private String state;

    @ApiModelProperty(value = "是否占用，0：占用，1：未占用")
    private Integer isTakeUp;

    @ApiModelProperty(value = "剩余车位数量")
    private  Integer spaceNumber;

}
