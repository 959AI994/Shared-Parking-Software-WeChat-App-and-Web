package com.lbk.sharper.shiro;

import com.lbk.sharper.entity.SysUser;
import com.lbk.sharper.service.ShiroService;
import com.lbk.sharper.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WJX
 * @Description 自定义 Realm
 * @Date 2023-06-03
 * @Time 23:58
 */
@Component
//定义 Realm 类，继承自 AuthorizingRealm 类，用于实现 Shiro 的身份验证和授权逻辑
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String userId = JwtUtil.getUsername(token);
        if (userId == null || !JwtUtil.verify(token, userId)) {
            throw new ConcurrentAccessException("token认证失败！");
        }

        //查询用户信息
        SysUser user = shiroService.queryUser(Integer.valueOf(userId));

        if (user == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        return new SimpleAuthenticationInfo(user, token, "MyRealm");


    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String userId = JwtUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
//        String role = usersMapper.getRole(username);
        String role = "admin";
        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(Integer.parseInt(userId));
        System.out.println("用户权限列表"+permsSet);
        List<String> rolePermission = new ArrayList<>();

        //每个用户可以设置新的权限
//        String permission = userMapper.getPermission(username);
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
        for (String item:rolePermission) {
            permissionSet.add(item);
        }
        roleSet.add(role);
//        permissionSet.add(rolePermission);
        //设置该用户拥有的角色和权限
//        info.setRoles(roleSet);
        
        info.setStringPermissions(permissionSet);
        return info;
    }
}
