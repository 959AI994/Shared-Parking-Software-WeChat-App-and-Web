package com.lbk.sharper.controller;

import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.entity.SysUser;
import com.lbk.sharper.service.SysUserRoleService;
import com.lbk.sharper.service.SysUserService;
import com.lbk.sharper.util.Constant;
import com.lbk.sharper.vo.SysUserVo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * @author WJX
 */
//用于处理与系统用户相关的HTTP请求。
// 它包含了获取用户列表、获取登录用户信息、获取用户详细信息、保存用户、修改用户和删除用户的方法。
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	 SysUserService sysUserService;
	@Autowired
	 SysUserRoleService sysUserRoleService;


	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	public Result list(SysUserVo sysUserVo){
		return ResultUtil.success(sysUserService.getSysUserList(sysUserVo));
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public Result info(){
		return ResultUtil.success("ok");
	}

	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	public Result<SysUser> info(@PathVariable("userId") Integer userId){
		SysUser user = sysUserService.getById(userId);
		
		//获取用户所属的角色列表
		List<Integer> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return ResultUtil.success(user);
	}
	
	/**
	 * 保存用户
	 */
	@PostMapping("/save")
	public Result save(SysUser user){
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		user.setCreateUserId(getUserId());
		sysUserService.saveUser(user);
		return ResultUtil.success(user);
	}
	
	/**
	 * 修改用户
	 */
	@PostMapping("/update")
	public Result update(@RequestBody SysUser user){

		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return ResultUtil.ok();
	}
	
	/**
	 * 删除用户
	 */
//	@SysLog("删除用户")
	@PostMapping("/delete")
//	@RequiresPermissions("sys:user:delete")
	public Result delete(@RequestBody Integer[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return ResultUtil.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return ResultUtil.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return ResultUtil.ok();
	}
}
