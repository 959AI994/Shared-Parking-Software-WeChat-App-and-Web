package com.lbk.sharper.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wjx
 * @Date: 2023/5/18 12:46
 * @Description
 * @Version 1.0
 */
@Data
public class AccessVO {
    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "页条数")
    private Integer pageSize ;
}
