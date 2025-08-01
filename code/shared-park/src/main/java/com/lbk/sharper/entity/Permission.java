package com.lbk.sharper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统权限
 * </p>
 *
 * @author WJX
 * @since 2023-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Permission对象", description = "系统权限")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "系统权限编号")
    @TableId(value = "permissionId", type = IdType.AUTO)
    private Integer permissionId;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "菜单url")
    private String url;

    @ApiModelProperty(value = "权限编码")
    private String code;

    @ApiModelProperty(value = "父id")
    @TableField("parentId")
    private Integer parentId;

    @ApiModelProperty(value = "类型，1：菜单，2：按钮")
    private Integer type;

    @ApiModelProperty(value = "权限描述")
    private String describes;

    @ApiModelProperty(value = "角色状态，0：禁用，1：启用")
    private Integer state;


}
