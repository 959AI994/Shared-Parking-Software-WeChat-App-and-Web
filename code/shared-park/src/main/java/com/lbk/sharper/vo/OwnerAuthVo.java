package com.lbk.sharper.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lbk.sharper.entity.OwnerAuth;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @Author: wjx
 * @Date: 2023/6/03 14:53
 * @Description
 * @Version 2.0
 */
@Data
//这个类的作用是传递业主认证信息的数据，
// 包含了一些字段来描述业主认证的各个方面，如业主编号、业主名称、车位产权证编号、电话、状态、身份证号、小区名称、性别、图片路径、车位号和不通过原因等。
public class OwnerAuthVo extends BaseVo {

    @ApiModelProperty(value = "业主编号")//通过使用 @ApiModelProperty 注解，可以为每个字段提供说明和描述，方便其他开发人员理解字段的含义和使用方式。
    private Integer ownerAuthId;

    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @ApiModelProperty(value = "车位产权证编号")
    private String parkingTileNumber;

    @ApiModelProperty(value = "电话")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "联系电话格式错误")
    private String phone;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "小区名称")
    private String villageName;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "图片路径")
    private String imgUrl;

    @ApiModelProperty(value = "车位号")
    private String parkingSpaceNumber;

    @ApiModelProperty(value = "不通过原因")
    private String notThroughReason;


}
