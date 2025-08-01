package com.lbk.sharper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.common.CustomException;
import com.lbk.sharper.entity.NormalUser;
import com.lbk.sharper.entity.ParkingOrder;
import com.lbk.sharper.entity.SysUser;
import com.lbk.sharper.mapper.SysUserMapper;
import com.lbk.sharper.service.SysRoleService;
import com.lbk.sharper.service.SysUserRoleService;
import com.lbk.sharper.service.SysUserService;
import com.lbk.sharper.util.Constant;
import com.lbk.sharper.vo.SysUserVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 系统用户
 *
 * @author wjx
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	@Autowired @Lazy
	 SysUserRoleService sysUserRoleService;
	@Autowired @Lazy
	 SysRoleService sysRoleService;

//	@Override
//	public PageUtils queryPage(Map<String, Object> params) {
//		String username = (String)params.get("username");
//		Long createUserId = (Long)params.get("createUserId");
//
//		IPage<SysUserEntity> page = this.page(
//			new Query<SysUserEntity>().getPage(params),
//			new QueryWrapper<SysUserEntity>()
//				.like(StringUtils.isNotBlank(username),"username", username)
//				.eq(createUserId != null,"create_user_id", createUserId)
//		);
//
//		return new PageUtils(page);
//	}

	@Override
	public IPage<SysUser> getSysUserList(SysUserVo sysUserVo) {
		IPage<SysUser> page = new Page<>(sysUserVo.getCurrent(), sysUserVo.getPageSize());
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
		return this.page(page, queryWrapper);
	}

	@Override
	public SysUser getPassword(String userName) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", userName);
		return this.getOne(queryWrapper);
	}

	@Override
	public List<String> queryAllPerms(Integer userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Integer> queryAllMenuId(Integer userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUser queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	@Transactional
	public void saveUser(SysUser user) {
		user.setCreateTime(LocalDateTime.now());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		this.save(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void update(SysUser user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}
		this.updateById(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	public void deleteBatch(Integer[] userId) {
		this.removeByIds(Arrays.asList(userId));
	}

	@Override
	public boolean updatePassword(Integer userId, String password, String newPassword) {
		SysUser userEntity = new SysUser();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new QueryWrapper<SysUser>().eq("user_id", userId).eq("password", password));
	}
	
	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUser user){
		if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
			return;
		}
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户创建的角色列表
		List<Integer> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

		//判断是否越权
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new CustomException("新增用户所选角色，不是本人创建");
		}
	}
}