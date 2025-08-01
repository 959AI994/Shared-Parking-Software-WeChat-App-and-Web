package com.lbk.sharper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 车位归属表
 * </p>
 *
 * @author WJX
 * @since 2023-6-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ParkingSpace对象", description = "车位归属表")
@TableName("parkingspace")
public class ParkingSpace implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "车位归属编号")
    @TableId(value = "parkingSpaceId",type = IdType.AUTO)
    private Integer parkingSpaceId;

    @ApiModelProperty(value = "车位号")
    @TableField("parkingSpaceNumber")
    private String parkingSpaceNumber;

    @ApiModelProperty(value = "停车场编号")
    @TableField("parkingInfoId")
    private Integer parkingInfoId;

    @ApiModelProperty(value = "归属业主编号")
    @TableField("ownerAuthId")
    private Integer ownerAuthId;

    @ApiModelProperty(value = "共享中，未共享，禁用")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "是否占用，0：占用，1：未占用")
    @TableField("isTakeUp")
    private Integer isTakeUp;

    @ApiModelProperty(value = "共享价格")
    @TableField("price")
    private Float price;

    @ApiModelProperty(value = "开始共享时间")
    @TableField("shareStartTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime shareStartTime;

    @ApiModelProperty(value = "结束共享时间")
    @TableField("shareEndTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime shareEndTime;



}
