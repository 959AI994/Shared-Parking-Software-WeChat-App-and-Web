package com.lbk.sharper.vo;

import com.lbk.sharper.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wjx
 * @Date: 2023/5/23 23:22
 * @Description
 * @Version 1.0
 */

@Data
public class SysUserVo extends SysUser {
    @ApiModelProperty(value = "当前页")
    private Integer current = 1;

    @ApiModelProperty(value = "页条数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "code")
    private String code;
}
