package com.lbk.sharper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.common.CustomException;
import com.lbk.sharper.entity.SysRole;
import com.lbk.sharper.mapper.SysRoleMapper;
import com.lbk.sharper.service.SysRoleMenuService;
import com.lbk.sharper.service.SysRoleService;
import com.lbk.sharper.service.SysUserRoleService;
import com.lbk.sharper.service.SysUserService;
import com.lbk.sharper.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
	@Autowired @Lazy
	 SysRoleMenuService sysRoleMenuService;
	@Autowired @Lazy
	 SysUserService sysUserService;
    @Autowired @Lazy
     SysUserRoleService sysUserRoleService;

//	@Override
//	public PageUtils queryPage(Map<String, Object> params) {
//		String roleName = (String)params.get("roleName");
//		Long createUserId = (Long)params.get("createUserId");
//
//		IPage<SysRoleEntity> page = this.page(
//			new Query<SysRoleEntity>().getPage(params),
//			new QueryWrapper<SysRoleEntity>()
//				.like(StringUtils.isNotBlank(roleName),"role_name", roleName)
//				.eq(createUserId != null,"create_user_id", createUserId)
//		);
//
//		return new PageUtils(page);
//	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRole role) {
        role.setCreateTime(new Date());
        this.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRole role) {
        this.updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }


    @Override
	public List<Integer> queryRoleIdList(Integer createUserId) {
		return baseMapper.queryRoleIdList(createUserId);
	}

	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRole role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户所拥有的菜单列表
		List<Integer> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
		
		//判断是否越权
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new CustomException("新增角色的权限，已超出你的权限范围");
		}
	}

	@Override
	public boolean saveBatch(Collection<SysRole> entityList) {
		return super.saveBatch(entityList);
	}
}
