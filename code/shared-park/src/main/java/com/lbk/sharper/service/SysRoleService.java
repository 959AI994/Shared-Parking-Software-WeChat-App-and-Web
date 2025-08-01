/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.lbk.sharper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbk.sharper.entity.SysRole;

import java.util.List;


/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
//此接口定义了角色相关的操作方法，
// 包括保存角色、更新角色、删除角色和查询指定用户创建的角色ID列表等。
public interface SysRoleService extends IService<SysRole> {

//	PageUtils queryPage(Map<String, Object> params);

	void saveRole(SysRole role);

	void update(SysRole role);

	void deleteBatch(Integer[] roleIds);

	
	/**
	 * 查询用户创建的角色ID列表
	 */
	//查询指定用户创建的角色ID列表。
	// 该方法根据传入的用户ID，在数据库中查询该用户所创建的角色ID列表，并返回结果。
	List<Integer> queryRoleIdList(Integer createUserId);
}
