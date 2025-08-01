package com.lbk.sharper.vo;

import com.lbk.sharper.entity.ParkingSpace;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wjx
 * @Date: 2023/5/16 15:21
 * @Description
 * @Version 1.0
 */
@Data
public class ParkingSpaceVo extends ParkingSpace {
    @ApiModelProperty(value = "当前页")
    private Integer current = 1;

    @ApiModelProperty(value = "页条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @ApiModelProperty(value = "小区名称")
    private String villageName;

}
