package com.lbk.sharper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 小区停车场表
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ParkingInfo对象", description = "小区停车场表")
@TableName(value = "parkinginfo")
public class ParkingInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "停车场编号")
    @TableId(value = "parkingInfoId", type = IdType.AUTO)
    private Integer parkingInfoId;

    @ApiModelProperty(value = "小区名称")
    @TableField("villageName")
    private String villageName;

    @ApiModelProperty(value = "地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "省")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "区")
    @TableField("area")
    private String area;

    @ApiModelProperty(value = "正常，禁用")
    private String state;

    @ApiModelProperty(value = "联系电话")
    private String phone;


    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private String latitude;


}
