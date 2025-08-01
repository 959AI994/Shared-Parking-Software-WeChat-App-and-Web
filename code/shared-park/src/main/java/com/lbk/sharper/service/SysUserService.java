package com.lbk.sharper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.entity.SysUser;
import com.lbk.sharper.vo.SysUserVo;
import com.lbk.sharper.vo.UserVo;

import java.util.List;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
//该接口定义了与系统用户相关的操作方法，包括查询、保存、更新、删除用户以及权限管理等。
public interface SysUserService extends IService<SysUser> {

//	PageUtils queryPage(Map<String, Object> params);
	IPage<SysUser> getSysUserList(SysUserVo sysUserVo);
	//根据传入的 SysUserVo 对象进行条件查询，返回符合条件的系统用户列表。
	SysUser getPassword(String userName);
///根据用户名查询用户的密码
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Integer userId);
	//查询用户的所有权限。
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Integer> queryAllMenuId(Integer userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUser queryByUserName(String username);

	/**
	 * 保存用户
	 */
	void saveUser(SysUser user);
	
	/**
	 * 修改用户
	 */
	void update(SysUser user);
	
	/**
	 * 删除用户
	 */
	void deleteBatch(Integer[] userIds);

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	boolean updatePassword(Integer userId, String password, String newPassword);
}
