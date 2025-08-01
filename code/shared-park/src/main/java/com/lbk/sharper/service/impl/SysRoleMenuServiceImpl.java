package com.lbk.sharper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.entity.SysRoleMenu;
import com.lbk.sharper.mapper.SysRoleMenuMapper;
import com.lbk.sharper.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 角色与菜单对应关系
 *
 * @author wjx
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Integer roleId, List<Integer> menuIdList) {
		//首先会删除指定角色的现有菜单关系，
		// 然后根据传入的菜单ID列表重新建立角色与菜单的关系。
		deleteBatch(new Integer[]{roleId});

		if(menuIdList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		for(Integer menuId : menuIdList){
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setMenuId(menuId);
			sysRoleMenu.setRoleId(roleId);

			this.save(sysRoleMenu);
		}
	}

	@Override
	//查询指定角色的菜单ID列表。根据角色ID查询与之关联的菜单ID列表。
	public List<Integer> queryMenuIdList(Integer roleId) {
		return baseMapper.queryMenuIdList(roleId);
	}

	@Override
	//批量删除角色与菜单的关系。根据角色ID数组批量删除对应的角色与菜单关系。
	public int deleteBatch(Integer[] roleIds){
		return baseMapper.deleteBatch(roleIds);
	}
	//该类通过调用 baseMapper 来执行数据库相关的操作，例如查询、插入和删除。
}
