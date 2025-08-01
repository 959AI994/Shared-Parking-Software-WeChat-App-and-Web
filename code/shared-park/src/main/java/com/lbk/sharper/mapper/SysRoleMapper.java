package com.lbk.sharper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbk.sharper.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Integer> queryRoleIdList(Integer createUserId);
}
