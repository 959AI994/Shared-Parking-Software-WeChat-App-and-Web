package com.lbk.sharper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbk.sharper.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Integer userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Integer> queryAllMenuId(Integer userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUser queryByUserName(String username);

}
