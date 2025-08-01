package com.lbk.sharper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 出入小区记录
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AccessRecords对象", description = "出入小区记录")
public class AccessRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "出入小区记录编号")
    @TableId(value = "accessRecordsId", type = IdType.AUTO)
    private Integer accessRecordsId;

    @ApiModelProperty(value = "车牌号")
    @TableField("carNumber")
    private String carNumber;

    @ApiModelProperty(value = "停车场编号")
    @TableField("parkingInfoId")
    private Integer parkingInfoId;

    @ApiModelProperty(value = "停车中，已完成")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "停车类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "进场时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("approachTime")
    private LocalDateTime approachTime;

    @ApiModelProperty(value = "出场时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("playingTime")
    private LocalDateTime playingTime;

    @ApiModelProperty(value = "支付金额")
    @TableField("paymentAmount")
    private Float paymentAmount;

    @ApiModelProperty(value = "支付方式")
    @TableField("methodPayment")
    private String methodPayment;


}
