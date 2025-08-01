package com.lbk.sharper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;

/**
 * <p>
 * 普通用户表
 * </p>
 *
 * @author WJX
 * @since 2023-5-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "NormalUser对象", description = "普通用户表")
@TableName("normaluser")
public class NormalUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "微信用户唯一标识")
    @TableId("openId")
    private String openId;

    @ApiModelProperty(value = "昵称")
    @TableField("nickName")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    @TableField("realName")
    private String realName;

    @ApiModelProperty(value = "性别")
    @TableField("gender")
    private String gender;


    @ApiModelProperty(value = "身份证号")
    @TableField("idNumber")
    private String idNumber;


    @ApiModelProperty(value = "状态")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "电话")
    @TableField("phone")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "联系电话格式错误")
    private String phone;

    @ApiModelProperty(value = "车牌号")
    @TableField("carNumber")
    private String carNumber;

    @ApiModelProperty(value = "余额")
    @TableField("balance")
    private Double balance;

    @ApiModelProperty(value = "是否是业主")
    @TableField("isOwner")
    private Integer isOwner;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Integer role_id;

    @ApiModelProperty(value = "头像")
    @TableField("avatarUrl")
    private String avatarUrl;











}
