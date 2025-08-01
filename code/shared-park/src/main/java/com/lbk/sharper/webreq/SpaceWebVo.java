package com.lbk.sharper.webreq;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author: wjx
 * @Date: 2023/5/20 21:27
 * @Description
 * @Version 1.0
 */
@Data
public class SpaceWebVo {
    @ApiModelProperty(value = "车位归属编号")
    private Integer parkingSpaceId;

    @ApiModelProperty(value = "共享中，未共享，禁用")
    @NotNull(message = "状态不能为空")
    private String state;

    @ApiModelProperty(value = "是否占用，0：占用，1：未占用")
    @NotNull(message = "是否占用不能为空")
    private Integer isTakeUp;

    @ApiModelProperty(value = "共享价格")
    @NotNull(message = "共享价格不能为空")
    private Float price;

    @ApiModelProperty(value = "开始共享时间")
    @NotNull(message = "开始共享时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shareStartTime;

    @ApiModelProperty(value = "结束共享时间")
    @NotNull(message = "结束共享时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shareEndTime;
}
