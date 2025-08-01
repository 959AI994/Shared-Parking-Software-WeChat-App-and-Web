package com.lbk.sharper.wx.wxvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: WJX
 * @Date: 2023/5/30 23:13
 * @Description 微信端分页
 * @Version 1.0
 */
@Data
public class BasePageVo {
    @ApiModelProperty(value = "当前页")
    private Integer current = 1;

    @ApiModelProperty(value = "页条数")
    private Integer pageSize = 10;
}
