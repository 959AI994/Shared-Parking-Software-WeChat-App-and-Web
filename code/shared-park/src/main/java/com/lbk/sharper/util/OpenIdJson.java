package com.lbk.sharper.util;

import lombok.Data;

/**
 * @Author: wjx
 * @Date: 2023/6/11 18:41
 * @Description OpenIdJson解析的实体类
 * @Version 1.0
 */
@Data
public class OpenIdJson {
    private String openid;
    private String session_key;
}
