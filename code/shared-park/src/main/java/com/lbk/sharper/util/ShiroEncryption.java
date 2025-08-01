package com.lbk.sharper.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Author: wjx
 * @Date: 2023/5/19 13:19
 * @Description
 * @Version 1.0
 */
public class ShiroEncryption {
    /***
     * 对用户的密码进行MD5加密
     * 做成工具类
     */
    public static String shiroEncryption(String password,String salt) {

        // shiro 自带的工具类生成salt
        //String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 加密次数
        int times = 2;
        // 算法名称
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();

        // 返回加密后的密码
        return encodedPassword;
    }
}
