package com.lbk.sharper.controller;

import com.lbk.sharper.common.Result;
import com.lbk.sharper.common.ResultUtil;
import com.lbk.sharper.entity.SysMenu;
import com.lbk.sharper.entity.SysUser;
import com.lbk.sharper.service.ISysMenuService;
import com.lbk.sharper.service.ShiroService;
import com.lbk.sharper.util.MapUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单
 * @author WJX
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController{
	@Autowired
	ISysMenuService iSysMenuService;
//	@Autowired
//	 ShiroService shiroService;

	/**
	 * 导航菜单
	 */
	@GetMapping("/nav")
	public Result nav(){ //它用于获取导航菜单数据
		List<SysMenu> menuList = iSysMenuService.getUserMenuList(getUserId());
		//来获取当前用户的菜单列表，并将结果封装到表示成功的 Result 对象中返回。
//		Set<String> permissions = shiroService.getUserPermissions(1);
		MapUtils map = new MapUtils().put("user_id", "admin").put("menu", menuList);
//		//。其中，"user_id" 是键，"admin" 是对应的值；"menu" 是键，menuList 是对应的值。
//		通过链式调用 put() 方法，可以连续添加多个键值对。
		return ResultUtil.success(map);
	}


	/**
	 * 所有菜单列表
	 */
	@GetMapping("/list")
//	@RequiresPermissions("sys:menu:list")
	public Result list(){
		List<SysMenu> menuList = iSysMenuService.list();
		for(SysMenu sysMenuEntity : menuList){
			SysMenu parentMenuEntity = iSysMenuService.getById(sysMenuEntity.getParentId());
			if(parentMenuEntity != null){
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}
		return ResultUtil.success(menuList);
	}

//	/**
//	 * 选择菜单(添加、修改菜单)
//	 */
//	@GetMapping("/select")
//	@RequiresPermissions("sys:menu:select")
//	public R select(){
//		//查询列表数据
//		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
//
//		//添加顶级菜单
//		SysMenuEntity root = new SysMenuEntity();
//		root.setMenuId(0L);
//		root.setName("一级菜单");
//		root.setParentId(-1L);
//		root.setOpen(true);
//		menuList.add(root);
//
//		return R.ok().put("menuList", menuList);
//	}
//
//	/**
//	 * 菜单信息
//	 */
//	@GetMapping("/info/{menuId}")
//	@RequiresPermissions("sys:menu:info")
//	public R info(@PathVariable("menuId") Long menuId){
//		SysMenuEntity menu = sysMenuService.getById(menuId);
//		return R.ok().put("menu", menu);
//	}
//
//	/**
//	 * 保存
//	 */
//	@SysLog("保存菜单")
//	@PostMapping("/save")
//	@RequiresPermissions("sys:menu:save")
//	public R save(@RequestBody SysMenuEntity menu){
//		//数据校验
//		verifyForm(menu);
//
//		sysMenuService.save(menu);
//
//		return R.ok();
//	}
//
//	/**
//	 * 修改
//	 */
//	@SysLog("修改菜单")
//	@PostMapping("/update")
//	@RequiresPermissions("sys:menu:update")
//	public R update(@RequestBody SysMenuEntity menu){
//		//数据校验
//		verifyForm(menu);
//
//		sysMenuService.updateById(menu);
//
//		return R.ok();
//	}
//
//	/**
//	 * 删除
//	 */
//	@SysLog("删除菜单")
//	@PostMapping("/delete/{menuId}")
//	@RequiresPermissions("sys:menu:delete")
//	public R delete(@PathVariable("menuId") long menuId){
//		if(menuId <= 31){
//			return R.error("系统菜单，不能删除");
//		}
//
//		//判断是否有子菜单或按钮
//		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
//		if(menuList.size() > 0){
//			return R.error("请先删除子菜单或按钮");
//		}
//
//		sysMenuService.delete(menuId);
//
//		return R.ok();
//	}
//
//	/**
//	 * 验证参数是否正确
//	 */
//	private void verifyForm(SysMenuEntity menu){
//		if(StringUtils.isBlank(menu.getName())){
//			throw new RRException("菜单名称不能为空");
//		}
//
//		if(menu.getParentId() == null){
//			throw new RRException("上级菜单不能为空");
//		}
//
//		//菜单
//		if(menu.getType() == Constant.MenuType.MENU.getValue()){
//			if(StringUtils.isBlank(menu.getUrl())){
//				throw new RRException("菜单URL不能为空");
//			}
//		}
//
//		//上级菜单类型
//		int parentType = Constant.MenuType.CATALOG.getValue();
//		if(menu.getParentId() != 0){
//			SysMenuEntity parentMenu = sysMenuService.getById(menu.getParentId());
//			parentType = parentMenu.getType();
//		}
//
//		//目录、菜单
//		if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
//				menu.getType() == Constant.MenuType.MENU.getValue()){
//			if(parentType != Constant.MenuType.CATALOG.getValue()){
//				throw new RRException("上级菜单只能为目录类型");
//			}
//			return ;
//		}
//
//		//按钮
//		if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
//			if(parentType != Constant.MenuType.MENU.getValue()){
//				throw new RRException("上级菜单只能为菜单类型");
//			}
//			return ;
//		}
//	}
}
