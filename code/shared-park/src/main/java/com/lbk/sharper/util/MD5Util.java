package com.lbk.sharper.util;


import java.security.MessageDigest;

/**
 * @Author: wjx
 * @Date: 2023/5/20 13:51
 * @Description
 * @Version 1.0
 */
//使用 Java 提供的 MessageDigest 类来执行 MD5 加密操作。
// 首先，它将输入的字符串转换为字符数组，
// 然后将字符数组转换为字节数组。
// 接下来，通过调用 MessageDigest 实例的 digest 方法对字节数组进行 MD5 加密，得到加密后的字节数组。
// 最后，它将字节数组转换为十六进制字符串表示形式，并返回结果。
public class MD5Util {

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
                md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16){
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    //: 这个方法用于对输入的字符串进行加密解密操作。
    // 它使用简单的异或运算进行加密和解密。
    // 首先，它将输入的字符串转换为字符数组，
    // 然后遍历字符数组，将每个字符与字符 't' 进行异或运算。
    // 最后，它将异或运算后的字符数组转换为字符串，并返回结果。
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);

    }

}
