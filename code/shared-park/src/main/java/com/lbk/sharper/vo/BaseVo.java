package com.lbk.sharper.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wjx
 * @Date: 2023/6/2 21:52
 * @Description 传递分页参数
 * @Version 1.0
 */
@Data
public class BaseVo {
    @ApiModelProperty(value = "当前页")
    private Integer current = 1;

    @ApiModelProperty(value = "页条数")
    private Integer pageSize = 10;
}
//这个类的作用是提供通用的分页参数，
// 可以在不同的数据传输对象中继承和重用。其他的数据传输对象可以继承 BaseVo 类，
// 并获取分页参数的功能，而无需重复定义相同的字段。