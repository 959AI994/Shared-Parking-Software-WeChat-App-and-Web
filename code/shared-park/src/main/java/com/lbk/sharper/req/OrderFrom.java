package com.lbk.sharper.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author: WJX
 * @Date: 2023/6/2 20:14
 * @Description
 * @Version 1.0
 */
@Data
//表示订单表单
public class OrderFrom {

    @ApiModelProperty(value = "车位归属号")
    @NotNull(message = "车位归属号不能为空")//如果在创建订单时没有提供有效的车位归属号，则会触发验证错误，错误消息为 "车位归属号不能为空"。
    private Integer parkingSpaceId;

//    @ApiModelProperty(value = "进场时间")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime approachTime;
//
//    @ApiModelProperty(value = "出场时间")
//    @NotNull(message = "出场时间不能为空")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime playingTime;
}
