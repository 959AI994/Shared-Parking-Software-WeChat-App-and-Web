package com.lbk.sharper.controller;

import com.lbk.sharper.service.SysRoleMenuService;
import com.lbk.sharper.service.SysRoleService;
import com.lbk.sharper.util.Constant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author Mark sunlightcs@gmail.com
 */
//系统角色管理的控制器类，用于处理与系统角色相关的HTTP请求。
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	 SysRoleService sysRoleService;
	@Autowired
	 SysRoleMenuService sysRoleMenuService;
//
//	/**
//	 * 角色列表
//	 */
//	@GetMapping("/list")
////	@RequiresPermissions("sys:role:list")
//	public R list(@RequestParam Map<String, Object> params){
//		//如果不是超级管理员，则只查询自己创建的角色列表
//		if(getUserId() != Constant.SUPER_ADMIN){
//			params.put("createUserId", getUserId());
//		}
//
//		PageUtils page = sysRoleService.queryPage(params);
//
//		return R.ok().put("page", page);
//	}
//
//	/**
//	 * 角色列表
//	 */
//	@GetMapping("/select")
////	@RequiresPermissions("sys:role:select")
//	public R select(){
//		Map<String, Object> map = new HashMap<>();
//
//		//如果不是超级管理员，则只查询自己所拥有的角色列表
//		if(getUserId() != Constant.SUPER_ADMIN){
//			map.put("create_user_id", getUserId());
//		}
//		List<SysRoleEntity> list = (List<SysRoleEntity>) sysRoleService.listByMap(map);
//
//		return R.ok().put("list", list);
//	}
//
//	/**
//	 * 角色信息
//	 */
//	@GetMapping("/info/{roleId}")
////	@RequiresPermissions("sys:role:info")
//	public R info(@PathVariable("roleId") Long roleId){
//		SysRoleEntity role = sysRoleService.getById(roleId);
//
//		//查询角色对应的菜单
//		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
//		role.setMenuIdList(menuIdList);
//
//		return R.ok().put("role", role);
//	}
//
//	/**
//	 * 保存角色
//	 */
//	@PostMapping("/save")
////	@RequiresPermissions("sys:role:save")
//	public R save(@RequestBody SysRoleEntity role){
//		ValidatorUtils.validateEntity(role);
//
//		role.setCreateUserId(getUserId());
//		sysRoleService.saveRole(role);
//
//		return R.ok();
//	}
//
//	/**
//	 * 修改角色
//	 */
////	@SysLog("修改角色")
//	@PostMapping("/update")
////	@RequiresPermissions("sys:role:update")
//	public R update(@RequestBody SysRoleEntity role){
//		ValidatorUtils.validateEntity(role);
//
//		role.setCreateUserId(getUserId());
//		sysRoleService.update(role);
//
//		return R.ok();
//	}
//
//	/**
//	 * 删除角色
//	 */
//	@SysLog("删除角色")
//	@PostMapping("/delete")
//	@RequiresPermissions("sys:role:delete")
//	public R delete(@RequestBody Long[] roleIds){
//		sysRoleService.deleteBatch(roleIds);
//
//		return R.ok();
//	}
}
