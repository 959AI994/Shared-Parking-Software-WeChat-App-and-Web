package com.lbk.sharper.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 业主信息表
 * </p>
 *
 * @author WJX
 * @since 2023-5-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OwnerAuth对象", description = "业主信息表")
@TableName("ownerauth")
public class OwnerAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业主编号")
    @TableId(value = "ownerAuthId", type = IdType.AUTO)
    private Integer ownerAuthId;

    @ApiModelProperty(value = "关联用户")
    @TableField("openId")
    private String openId;

    @ApiModelProperty(value = "业主名称")
    @TableField("ownerName")
    private String ownerName;

    @ApiModelProperty(value = "车位产权证编号")
    @TableField("parkingTileNumber")
    private String parkingTileNumber;

    @ApiModelProperty(value = "证明图片路径")
    @TableField("imgUrl")
    private String imgUrl;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "身份证号")
    @TableField("idNumber")
    private String idNumber;

    @ApiModelProperty(value = "小区名称")
    @TableField("villageName")
    private String villageName;

    @ApiModelProperty(value = "车位号")
    @TableField("parkingSpaceNumber")
    private String parkingSpaceNumber;

    @ApiModelProperty(value = "不通过原因")
    @TableField(value = "notThroughReason",strategy = FieldStrategy.IGNORED)
    private String notThroughReason;


}
