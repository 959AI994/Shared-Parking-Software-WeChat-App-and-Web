package com.lbk.sharper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色与菜单对应关系
 * @author WJX
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;

	/**
	 * 角色ID
	 */
	private Integer roleId;

	/**
	 * 菜单ID
	 */
	private Integer menuId;
	
}
