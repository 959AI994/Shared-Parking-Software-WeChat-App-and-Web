/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.lbk.sharper.common.validator;


import com.lbk.sharper.common.CustomException;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据校验
 *
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new CustomException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new CustomException(message);
        }
    }
}
