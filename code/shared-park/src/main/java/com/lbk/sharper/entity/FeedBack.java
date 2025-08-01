package com.lbk.sharper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  用户反馈表
 * </p>
 *
 * @author WJX
 * @since 2023-5-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "FeedBack对象", description = "")
public class FeedBack implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id编号")
    @TableId(value = "feedBackId", type = IdType.AUTO)
    private Integer feedBackId;

    @ApiModelProperty(value = "用户id")
    @TableField("openId")
    private String openId;

    @ApiModelProperty(value = "反馈内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private LocalDateTime createTime;


}
