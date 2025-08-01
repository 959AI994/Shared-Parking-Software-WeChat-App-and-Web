package com.lbk.sharper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.entity.SysUserRole;
import com.lbk.sharper.mapper.SysUserRoleMapper;
import com.lbk.sharper.service.SysUserRoleService;
import com.lbk.sharper.util.MapUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户与角色对应关系
 *
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

	@Override
	public void saveOrUpdate(Integer userId, List<Integer> roleIdList) {
		//先删除用户与角色关系
		this.removeByMap(new MapUtils().put("user_id", userId));

		if(roleIdList == null || roleIdList.size() == 0){
			return ;
		}

		//保存用户与角色关系
		for(Integer roleId : roleIdList){
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(userId);
			sysUserRole.setRoleId(roleId);

			this.save(sysUserRole);
		}
	}

	@Override
	public List<Integer> queryRoleIdList(Integer userId) {
		return baseMapper.queryRoleIdList(userId);
	}

	@Override
	public int deleteBatch(Integer[] roleIds){
		return baseMapper.deleteBatch(roleIds);
	}
}
