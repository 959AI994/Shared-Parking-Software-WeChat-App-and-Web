package com.lbk.sharper.service.impl;


import com.lbk.sharper.entity.SysUser;
import com.lbk.sharper.mapper.SysUserMapper;
import com.lbk.sharper.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    SysUserMapper sysUserMapper;


    @Override
    public Set<String> getUserPermissions(Integer userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
//        if(userId == Constant.SUPER_ADMIN){
//            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
//            permsList = new ArrayList<>(menuList.size());
//            for(SysMenuEntity menu : menuList){
//                permsList.add(menu.getPerms());
//            }
//        }else{
//            permsList = sysUserDao.queryAllPerms(userId);
//        }
//        //用户权限列表
//        Set<String> permsSet = new HashSet<>();
//        for(String perms : permsList){
//            if(StringUtils.isBlank(perms)){
//                continue;
//            }
//            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
//        }
//        return permsSet;
        return null;
    }

//    @Override
//    public SysUserTokenEntity queryByToken(String token) {
//        return sysUserTokenDao.queryByToken(token);
//    }

    @Override
    public SysUser queryUser(Integer userId) {
        return sysUserMapper.selectById(userId);
    }
}
