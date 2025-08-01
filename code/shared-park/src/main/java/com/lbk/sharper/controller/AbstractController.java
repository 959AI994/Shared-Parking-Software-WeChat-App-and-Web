package com.lbk.sharper.controller;

import com.lbk.sharper.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 * @author WJX
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	//记录日志的 Logger 对象。通过 getLogger(getClass()) 方法获取与实现类相关的 Logger 对象。
	protected SysUser getUser() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}
//用于获取当前登录用户的信息。
// 通过 SecurityUtils.getSubject().getPrincipal() 方法获取当前登录用户的主体对象，并将其转换为 SysUser 类型返回。
	protected Integer getUserId() {
		return getUser().getUserId();
	}
	//用于获取当前登录用户的ID
}
