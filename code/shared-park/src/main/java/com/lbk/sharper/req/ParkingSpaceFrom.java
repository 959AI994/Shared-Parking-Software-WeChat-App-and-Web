package com.lbk.sharper.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

/**
 * @Author: WJX
 * @Date: 2023/6/6 21:27
 * @Description
 * @Version 1.0
 */
@Data
public class ParkingSpaceFrom {

    @ApiModelProperty(value = "开始共享时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "开始共享时间不能为空")
    private LocalDateTime shareStartTime;

    @ApiModelProperty(value = "结束共享时间")
    @NotNull(message = "结束共享时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shareEndTime;

    @ApiModelProperty(value = "价钱")
    @NotNull(message = "价钱不能为空")
    private Float price;


}
