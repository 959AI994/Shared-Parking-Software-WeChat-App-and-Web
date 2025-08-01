package com.lbk.sharper.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lbk.sharper.common.CustomException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WJX
 * @Date: 2023/5/14 11:19
 * @Description
 * @Version 1.0
 */
//用于通过 API 获取地址信息和计算两个定位点之间的距离。
@Component
public class GouldUtil {
    //在高德地图申请的应用Key
    private static final String GOULD_KEY= "aa633e39195308ea65effedb1d9a85c5";
//存储高德地图申请的应用 Key

    /**
     * 0.根据地址名称得到两个地址间的距离
     * @param start 起始位置
     * @param start 结束位置
     * @return 两个地址间的距离
     */
    //根据地址名称获取两个地址之间的距离
    public long getDistanceByAddress(String start, String end) {
//        String startLonLat = getLonLat(start);
//        String endLonLat = getLonLat(end);
//        long dis = getDistance(startLonLat, endLonLat);
        return 0;
    }

    /**
     * 1.地址转换为经纬度
     * @param address 地址
     * @return 经纬度
     */
    public Map getLonLat(String address) {
        // 返回输入地址address的经纬度信息, 格式是 经度,纬度
        //用于将地址转换为经纬度。它接受一个参数 address，表示要转换的地址。
        // 它会调用腾讯地图 API，将地址发送给 API，并获取返回的经纬度信息。
        // 最后，它将经纬度信息存储在一个 Map 对象中，并返回该对象。
        String queryUrl = "http://restapi.amap.com/v3/geocode/geo?key="+GOULD_KEY+"&address=" + address;
        // 高德接品返回的是JSON格式的字符串
        String queryResult = getResponse(queryUrl);
        JSONObject job = JSONObject.parseObject(queryResult);
        JSONObject jobJSON = JSONObject
                .parseObject(job.get("geocodes").toString().substring(1, job.get("geocodes").toString().length() - 1));
       if(jobJSON==null){
           throw new CustomException("请输入正确的详细地址");
       }
        String DZ = jobJSON.get("location").toString();

        String[] res = new String[2];
        String[] strArray = DZ.split(",");
        for (int i = 0; i < strArray.length; i++) {
            System.out.println(i+":" + strArray[i]);
            res[i] = strArray[i];
        }

        Map<String, String> map = new HashMap<>();
        map.put("longitude", res[0]);
        map.put("latitude", res[1]);
        return map;
    }

    /**
     * 将经纬度getLng， getLat 通过getAMapByLngAndLat方法转换地址
     * @param getLng 经度
     * @param getLat 纬度
     * @return 地址名称
     * @throws Exception
     */
    public String getAMapByLngAndLat(String getLng, String getLat) throws Exception {
        //这个方法用于根据经纬度获取地址信息。
        // 它接受两个参数：经度 getLng 和纬度 getLat。
        // 它会调用高德地图 API，将经纬度发送给 API，并获取返回的地址信息。
        // 最后，它将地址信息作为字符串返回。
        String url;
        try {
            url = "http://restapi.amap.com/v3/geocode/regeo?output=JSON&location=" + getLng + "," + getLat
                    + "&key="+GOULD_KEY+"&radius=0&extensions=base";
            System.out.println(getLat);
            System.out.println(url);
            String queryResult = getResponse(url); // 高德接品返回的是JSON格式的字符串
            if (queryResult == null) {
                return "-1";
            }
            // 将获取结果转为json 数据
//            .stringToValue(queryResult)
            JSONObject obj = JSONObject.parseObject(queryResult);
            System.out.println("obj为++++++++++++++++++++++"+obj);
            if (obj.get("status").toString().equals("1")) {
                // 如果没有返回-1
                JSONObject regeocode = obj.getJSONObject("regeocode");
                if (regeocode.size() > 0) {
                    // 在regeocode中拿到 formatted_address 具体位置
                    String formatted = regeocode.get("formatted_address").toString();
                    return formatted;

                } else {
                    System.out.println("未找到相匹配的地址！");
                    return "-1";

                }
            } else {
                System.out.println("请求错误！");
                return "-1";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "-1";
    }

    /**
     * 2.根据两个定位点的经纬度算出两点间的距离
     * @param startLonLat 起始经纬度
     * @param endLonLat 结束经纬度（目标经纬度）
     * @return 两个定位点之间的距离
     */
    private long getDistance(String startLonLat, String endLonLat) {
        // 返回起始地startAddr与目的地endAddr之间的距离，单位：米
        //根据两个定位点的经纬度计算它们之间的距离。
        // 它接受两个参数：起始经纬度 startLonLat 和目标经纬度 endLonLat。
        // 它会调用腾讯地图 API，将经纬度发送给 API，并获取返回的距离信息。
        // 最后，它将距离作为长整型返回。
        Long result = new Long(0);
        String queryUrl = "http://restapi.amap.com/v3/distance?key="+GOULD_KEY+"&origins=" + startLonLat + "&destination="
                + endLonLat;
        String queryResult = getResponse(queryUrl);
        JSONObject job = JSONObject.parseObject(queryResult);
        JSONArray ja = job.getJSONArray("results");
        JSONObject jobO = JSONObject.parseObject(ja.getString(0));
        result = Long.parseLong(jobO.get("distance").toString());
//		System.out.println("距离2：" + result);
        return result;
    }

    /**
     * 3.发送请求
     * @param serverUrl 请求地址
     */
    private static String getResponse(String serverUrl) {
        // 用JAVA发起http请求，并返回json格式的结果
        //这个私有方法用于发送 HTTP 请求并获取响应结果。
        // 它接受一个参数 serverUrl，表示要请求的 URL 地址。
        // 它会创建一个 URL 对象，并打开一个连接。
        // 然后，它使用 BufferedReader 逐行读取响应内容，并将其拼接成一个字符串，
        // 最后将字符串作为结果返回。
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
