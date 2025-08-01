package com.lbk.sharper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 停车订单
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
@Data//@Data 是 Lombok 注解，它会自动生成类的 getter、setter、equals、hashCode 和 toString 方法。
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ParkingReservation对象", description = "预约停车")
//@ApiModel 是 Swagger 注解，用于描述一个 API 模型对象。
public class ParkingOrder implements Serializable {//表示 ParkingOrder 类是可序列化的。

    private static final long serialVersionUID = 1L;
//每个属性都具有 @ApiModelProperty 注解用于描述属性的含义。
    @ApiModelProperty(value = "停车订单号")
    @TableId(value = "orderId" )//MyBatis Plus 的注解之一，用于指定实体类的属性与数据库表的主键字段之间的映射关系。
    private String orderId;
//通过在实体类中使用 @TableId 注解，你告诉 MyBatis Plus 在进行数据库操作时，将使用该属性作为主键字段。
// 这样，在进行插入、更新、删除等操作时，MyBatis Plus 将自动将该属性的值映射到数据库表的主键字段。
    @ApiModelProperty(value = "微信用户唯一标识")
    @TableField("openId")
    private String openId;

    @ApiModelProperty(value = "车位归属号")
    @TableField("parkingSpaceId")
    private Integer parkingSpaceId;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "进行中，已完成，取消")
    private String state;

    @ApiModelProperty(value = "停车时长")
    @TableField("parkingDuration")
    private String parkingDuration;

    @ApiModelProperty(value = "支付状态")
    @TableField("paymentStatus")
    private String paymentStatus;

    @ApiModelProperty(value = "进场时间")
    @TableField("approachTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime approachTime;

    @ApiModelProperty(value = "实际出场时间")
    @TableField("actualPlayingTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime actualPlayingTime;

    @ApiModelProperty(value = "支付金额")
    @TableField("paymentAmount")
    private Float paymentAmount;

    @ApiModelProperty(value = "支付时间")
    @TableField("paymentTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime paymentTime;


}
