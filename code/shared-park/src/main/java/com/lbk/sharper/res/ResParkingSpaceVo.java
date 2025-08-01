package com.lbk.sharper.res;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Author: WJX
 * @Date: 2023/6/1 23:13
 * @Description
 * @Version 1.0
 */
//传递停车位的信息，并包含了与停车位相关的各种字段。
@Data
public class ResParkingSpaceVo {

    @ApiModelProperty(value = "车位编号")
    private Integer parkingSpaceId;

    @ApiModelProperty(value = "车位号")
    private String parkingSpaceNumber;

    @ApiModelProperty(value = "停车场名称")
    private String villageName;

    @ApiModelProperty(value = "停车场地址")
    private String address;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;


    @ApiModelProperty(value = "归属业主")
    private Integer ownerAuthId;

    @ApiModelProperty(value = "是否占用，0：占用，1：未占用")
    private Integer isTakeUp;

    @ApiModelProperty(value = "共享中，未共享，禁用")
    private String state;

    @ApiModelProperty(value = "开始共享时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime shareStartTime;

    @ApiModelProperty(value = "结束共享时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime shareEndTime;

    @ApiModelProperty(value = "共享停车价钱")
    private Float sharePrice;

    @ApiModelProperty(value = "价钱")
    private Float price;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "业主名称")
    @TableField("ownerName")
    private String ownerName;

}
