package com.lbk.sharper;

import com.lbk.sharper.res.RemainingSpaceVo;
import com.lbk.sharper.service.IParkingInfoService;
import com.lbk.sharper.service.IParkingOrderService;
import com.lbk.sharper.util.DateUtils;
import com.lbk.sharper.util.LocationUtils;
import com.lbk.sharper.util.MD5Util;
import com.lbk.sharper.vo.ParkingInfoVo;
import com.lbk.sharper.wx.res.ParkingInfoVoWx;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class SharedParkApplicationTests {

    @Autowired
    IParkingOrderService iParkingOrderService;
    @Autowired
    IParkingInfoService iParkingInfoService;


    /**
     * 此测试方法主要用于计算两个日期之间的间隔，并以天数，小时数和分钟数的形式输出。
     * 然后，它会将分钟数转换为小时数，最后将得到的浮点数舍入到两位小数
     */
    @Test
    void contextLoads() {
//        List<String> rolePermission = userMapper.getRolePermission("admin");
//        for (String ite:rolePermission
//             ) {
//            System.out.println("testLLLLL"+ite);
//        }
        LocalDateTime sta = DateUtils.parseLocalDateTime("2023-6-09 09:00:00");
        LocalDateTime end = DateUtils.parseLocalDateTime("2023-6-11 23:10:00");
        Duration duration = Duration.between(sta,end);
        //天数
        long tian= duration. toDays();
        //小时
        long shi =duration. toHours();
        //相差的分钟数
        long fen = duration.toMinutes();
        Float paringHours = null;
        paringHours= Float.valueOf((float) (fen/60.0));

        if(shi<=0){
            paringHours = Float.valueOf(fen);
        }
        DecimalFormat decimalFormat= new  DecimalFormat( ".00" ); //构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p=decimalFormat.format(paringHours); //for
        float b = Float.parseFloat(p);

    }

    /**
     * 测试方法是用于计算并格式化给定分钟数到"小时+分钟"格式。
     * 如果给定的分钟数可以整除60（即它是一个小时的倍数），则它仅显示小时数。
     * 否则，它将显示小时数和剩余的分钟数。
     */
    @Test
    void testLoginstime() {

//        LocalDateTime sta = DateUtils.parseLocalDateTime("2021-11-29 09:00:00");
//        LocalDateTime end = DateUtils.parseLocalDateTime("2021-12-01 12:20:00");
//
//        Duration duration = Duration.between(sta,end);
//        long days = duration.toDays(); //相差的天数
//        long hours = duration.toHours();//相差的小时数
//        long minutes = duration.toMinutes();//相差的分钟数
//        long millis = duration.toMillis();//相差毫秒数
//        long nanos = duration.toNanos();//相差的纳秒数
//        System.out.println("发送短信耗时【 "+days+"天："+hours+" 小时："+minutes+" 分钟："+millis+" 毫秒："+nanos+" 纳秒】");
//

        String resultStr = "";

        int minuteStr = 30;

            if (minuteStr != 0) {
                if (minuteStr/60 == 0) {
                    resultStr = minuteStr%60+"分";
                }else{
                    if (minuteStr%60 == 0) {
                        resultStr = minuteStr/60+"小时";
                    }else{
                        resultStr = (minuteStr/60+"小时"+minuteStr%60+"分");
                    }
                }
            }else{
                resultStr = "0时0分";
            }

        System.out.println(resultStr);


    }

    /**
     * 使用LocationUtils.getDistance()方法计算两个地点之间的距离，并输出结果。
     */

    @Test
    void testLocationUtils() {

        double i3=110.1961226584192;
        double i4=22.68134858790234;
        double i5=110.16100643412562;
        double i6=22.641828363442407;


        double ite = LocationUtils.getDistance(140.16063765363458, 30.64247770121276,i3 ,i4 );

            System.out.println("testLLLLL-------"+ite);
    }

    @Test
    void getUUID() {

//        System.out.println("uuid-------"+ UUID.randomUUID());


    }
    @Test
    void mdg5() {
        String s = "123456";
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String res= new Sha256Hash(s, salt).toHex();
        System.out.println(salt+"加密后"+res);

    }

    /**
     * 这个测试方法使用MD5散列函数对一个给定的字符串进行散列，并输出结果。
     * 然后，它会使用一个自定义的MD5工具方法对字符串进行加密和解密。
     */

    @Test
    void md5() {
        String s = "lbk";
        System.out.println("MD5后：" + MD5Util.string2MD5(s));
        System.out.println("加密的：" + MD5Util.convertMD5(s).toString());
        System.out.println("解密的：" + MD5Util.convertMD5( MD5Util.convertMD5(s)));

        String md5Password = DigestUtils.md5DigestAsHex(s.getBytes());


    }

    /**
     * 将一个以逗号分隔的字符串分割成一个字符串数组，然后打印出每个元素。
     */
    @Test
    void getsubstring() {

         StringBuffer strB = new StringBuffer();
        //目标字符串
        String str = "A,B,C,D,E,F";
        //截取获得字符串数组
        String[] strArray = str.split(",");
        for (int i = 0; i < strArray.length; i++) {
            System.out.println(i+":" + strArray[i]);
        }
        System.out.println("result:" + strB);
    }

    /**
     * 从一个服务获取一个ParkingInfoVoWx对象列表，然后根据每个对象的VillageName属性将它们分组。
     * 然后，对每个组，它会计算并设置IsTakeUp属性为0的对象的数量，并将结果添加到一个新的列表中。
     */

    @Test
    void tesk() {

        List<ParkingInfoVoWx> parkingInfoVoWxList = iParkingInfoService.getListMap();

        Map<String, List<ParkingInfoVoWx>> jk = parkingInfoVoWxList.stream().collect(Collectors.groupingBy(ParkingInfoVoWx::getVillageName));

        List<ParkingInfoVoWx> resList = new ArrayList<>();
        for (String s : jk.keySet()) {
            List<ParkingInfoVoWx> value = jk.get(s);
            int count = 0;
            ParkingInfoVoWx park = new ParkingInfoVoWx();
            for (ParkingInfoVoWx parkingInfoVoWx : value) {
                park = parkingInfoVoWx;
                if (parkingInfoVoWx.getIsTakeUp() == 0) {
                    count++;
                }
            }
            park.setSpaceNumber(count);
            resList.add(park);
        }

    }

  // @Test



}
