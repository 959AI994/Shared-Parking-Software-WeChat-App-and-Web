package com.lbk.sharper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbk.sharper.entity.SysUserRole;

import java.util.List;


/**
 * 用户与角色对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserRoleService extends IService<SysUserRole> {
	
	void saveOrUpdate(Integer userId, List<Integer> roleIdList);
	//据传入的用户ID和角色ID列表，保存或更新数据库中的用户角色关系。
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Integer> queryRoleIdList(Integer userId);
//根据传入的用户ID，在数据库中查询该用户所关联的角色ID列表，并返回结果。
	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Integer[] roleIds);
	//根据传入的角色ID数组，批量删除数据库中对应的用户角色关系。
}
