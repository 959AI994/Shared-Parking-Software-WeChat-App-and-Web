package com.lbk.sharper.vo;

import com.lbk.sharper.entity.ParkingInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wjx
 * @Date: 2023/5/16 13:59
 * @Description
 * @Version 1.0
 */
@Data
public class ParkingInfoVo extends ParkingInfo {

    @ApiModelProperty(value = "当前页")
    private Integer current = 1;

    @ApiModelProperty(value = "页条数")
    private Integer pageSize = 10;



}
