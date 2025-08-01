package com.lbk.sharper.wx.annotation;

import java.lang.annotation.*;

/**
 * 微信登录效验
 *
 * @author Mark sunlightcs@gmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
