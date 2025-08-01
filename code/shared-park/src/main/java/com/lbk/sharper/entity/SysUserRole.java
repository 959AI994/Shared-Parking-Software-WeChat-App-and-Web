package com.lbk.sharper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 * @author WJX
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Integer id;

	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 角色ID
	 */
	private Integer roleId;

	
}
