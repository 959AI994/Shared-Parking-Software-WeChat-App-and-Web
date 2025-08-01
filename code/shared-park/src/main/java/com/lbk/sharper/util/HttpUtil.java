package com.lbk.sharper.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.httpclient.HttpStatus;

/**
 * @Author: wjx
 * @Date: 2023/6/4 18:39
 * @Description
 * @Version 1.0
 */
public class HttpUtil {
    public static String doGet(String urlPath, HashMap<String, Object> params)
            throws Exception {
        //这个方法用于发送 HTTP GET 请求并获取响应结果。它接受两个参数：
        // 请求的 URL 地址 urlPath 和请求的参数 params，参数是一个 HashMap 类型的对象，
        // 其中包含请求的键值对参数。
        StringBuilder sb = new StringBuilder(urlPath);
        //这行代码创建了一个 StringBuilder 对象，用于构建完整的 URL 地址。
        if (params != null && !params.isEmpty()) { // 说明有参数
            //检查参数 params 是否为空，如果不为空，则需要将参数拼接到 URL 地址中。
            sb.append("?");

            Set<Entry<String, Object>> set = params.entrySet();
            for (Entry<String, Object> entry : set) { // 遍历map里面的参数
                String key = entry.getKey();
                String value = "";
                if (null != entry.getValue()) {
                    value = entry.getValue().toString();
                    // 转码
                    value = URLEncoder.encode(value, "UTF-8");
                }
                sb.append(key).append("=").append(value).append("&");
            }

            sb.deleteCharAt(sb.length() - 1); // 删除最后一个&
        }
        // System.out.println(sb.toString());
        URL url = new URL(sb.toString());
        //这行代码创建了一个 URL 对象，并使用它打开一个 HttpURLConnection 连接。
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000); // 5s超时
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == HttpStatus.SC_OK) {// HttpStatus.SC_OK ==
            // 200
            //这个条件判断语句检查响应的状态码是否为 200，表示请求成功。
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            StringBuilder sbs = new StringBuilder();
            // 这行代码创建了一个 BufferedReader 对象，用于读取响应内容。
            String line;
            while ((line = reader.readLine()) != null) {
                sbs.append(line);
                //这个循环逐行读取响应内容，并将其拼接到 StringBuilder 对象 sbs 中。
            }
            return sbs.toString();//这行代码返回读取到的响应内容。
        }

        return null;
    }
}
