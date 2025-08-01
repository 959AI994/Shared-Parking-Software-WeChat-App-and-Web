package com.lbk.sharper.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: WJX
 * @Date: 2023/5/28 14:16
 * @Description
 * @Version 1.0
 */
@Data
//这个类的作用是传递出入小区记录的数据。
// 它包含了一些字段来描述出入小区记录，如记录编号、小区名称、车牌号、停车场编号、状态、停车类型、进场时间和出场时间等。
public class ResAccessRecordsVo {
    @ApiModelProperty(value = "出入小区记录id")
    private Integer accessRecordsId;

    @ApiModelProperty(value = "小区名称")
    private String villageName;

    @ApiModelProperty(value = "车牌号")
    private String carNumber;

    @ApiModelProperty(value = "停车场编号")
    private Integer parkingInfoId;

    @ApiModelProperty(value = "停车中，已完成")
    private String state;

    @ApiModelProperty(value = "停车类型")
    private String type;

    @ApiModelProperty(value = "进场时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //通过使用 @JsonFormat 注解，指定了时间字段的格式，以便在传输和显示时按照指定的格式进行格式化。
    private LocalDateTime approachTime;

    @ApiModelProperty(value = "出场时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime playingTime;

}
