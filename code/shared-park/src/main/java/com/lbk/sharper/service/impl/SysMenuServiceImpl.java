package com.lbk.sharper.service.impl;

import com.lbk.sharper.entity.SysMenu;
import com.lbk.sharper.mapper.SysMenuMapper;
import com.lbk.sharper.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbk.sharper.service.SysRoleMenuService;
import com.lbk.sharper.service.SysUserService;
import com.lbk.sharper.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author WJX
 * @since 2023-6-07
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    SysMenuServiceImpl(SysUserService sysUserService,SysRoleMenuService sysRoleMenuService){
        this.sysUserService=sysUserService;
        this.sysRoleMenuService= sysRoleMenuService;
    }
//    @Autowired
    SysUserService sysUserService;
//    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Override
    //根据给定的父菜单ID和菜单ID列表查询符合条件的菜单列表。
    public List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);//创建一个空的 subMenuList 列表，用于存储子菜单对象。
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for(SysMenu menu : menuList){//遍历传入的菜单列表 menuList 中的每个菜单对象 entity。
            if(menuIdList.contains(menu.getMenuId())){
               // menu.setComponent("Layout");
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    //根据给定的父菜单ID，查询符合条件的菜单列表
    public List<SysMenu> queryListParentId(Integer parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    //查询所有非按钮类型的菜单列表。
    public List<SysMenu> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    //根据给定的用户ID，获取用户的菜单列表。
    public List<SysMenu> getUserMenuList(Integer userId) {
        //系统管理员，拥有最高权限
//        if(userId == Constant.SUPER_ADMIN){
//            return getAllMenuList(null);
//        }

        //用户菜单列表
		List<Integer> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
    }

    @Override
    //删除指定ID的菜单
    public void delete(Integer menuId){
        //删除菜单
        this.removeById(menuId);
        //删除菜单与角色关联
//		sysRoleMenuService.removeByMap(new MapUtils().put("menu_id", menuId));


    }

    /**
     * 获取所有菜单列表
     */
    //获取系统中的所有菜单列表。首先查询根菜单列表，
    // 然后通过递归调用 getMenuTreeList 方法获取子菜单列表。
    private List<SysMenu> getAllMenuList(List<Integer> menuIdList){
        //查询根菜单列表
        List<SysMenu> menuList = queryListParentId(0, menuIdList);
        //递归获取子菜单



        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    //该方法的作用是递归地构建菜单树
    //构建菜单树的目的是为了组织和展示具有层级结构的菜单数据。菜单树的构建可以将扁平的菜单数据转化为层级结构，使菜单之间形成父子关系，方便进行展示和操作。
    //通过递归调用，在给定的菜单列表中获取子菜单列表，并设置菜单的元数据。
    // 如果菜单是目录类型，则继续递归调用 getMenuTreeList 方法获取其子菜单。
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Integer> menuIdList){
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();

        for(SysMenu entity : menuList){//遍历传入的菜单列表 menuList 中的每个菜单对象 entity
            //目录
            if (entity.getType() == Constant.MenuType.CATALOG.getValue()) {
                entity.setChildren(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            } else {
                entity.setRedirect(null);
            }
            HashMap<String, String> meta = new HashMap<>();
            meta.put("title", entity.getName());
            meta.put("icon", entity.getIcon());
            entity.setMeta(meta);

            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
