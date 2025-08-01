package com.lbk.sharper.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Author: WJX
 * @Date: 2023/6/7 13:18
 * @Description 添加停车场表单校验
 * @Version 1.0
 */
@Data
public class ParkingInfoFrom  implements Serializable {

    @ApiModelProperty(value = "停车场编号")
    private Integer parkingInfoId;

    @ApiModelProperty(value = "小区名称")
    @NotBlank(message = "小区名称不能为空")
    private String villageName;

    @ApiModelProperty(value = "省")
    @NotBlank(message = "省不能为空")
    private String province;

    @ApiModelProperty(value = "市")
    @NotBlank(message = "市不能为空")
    private String city;

    @ApiModelProperty(value = "区")
    @NotBlank(message = "区不能为空")
    private String area;


    @ApiModelProperty(value = "地址")
    @NotBlank(message = "地址不能为空")
    private String address;

    @ApiModelProperty(value = "正常，禁用")
//    @NotBlank(message = "状态不能为空")
    private String state;

    @ApiModelProperty(value = "联系电话")
    @NotBlank(message = "联系电话不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "联系电话格式错误")
    private String phone;

    @ApiModelProperty(value = "经度")
    @NotBlank(message = "经度不能为空")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    @NotBlank(message = "纬度不能为空")
    private String latitude;

}
