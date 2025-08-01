package com.lbk.sharper.util;

import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static com.baomidou.mybatisplus.core.toolkit.ObjectUtils.isEmpty;

/**
 * @Author: WJX
 * @Date: 2023/5/22 14:26
 * @Description 时间工具类
 * @Version 1.0
 */
public class DateUtils {
    private DateUtils() {
    }

    private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_FORMATTER = "yyyy-MM-dd";

    public static final String FORMAT_DATE_y2M = "yyMM";//例：1908  				  表示 19年8月
    public static final String FORMAT_DATE_y2Md = "yyMMdd";//例：190816  			  表示 19年8月16日
    public static final String FORMAT_DATE_y4 = "yyyy";//例：2019  				  表示 2019年
    public static final String FORMAT_DATE_y4Md = "yyyyMMdd"; //例：20190816  			  表示 2019年8月16日
    public static final String FORMAT_DATE_Y4MD = "yyyy-MM-dd"; //例：2019-08-16  		  表示 2019年8月16日
    public static final String FORMAT_TIMESTAMP = "yyMMddhhmmss";//例：190816121212  		  表示 19年8月16日12时12分12秒
    public static final String FORMAT_TIME_HHmm = "HH:mm";//例：12:30  			      表示 12点30分
    public static final String FORMAT_TIME_HHmmss = "HH:mm:ss";//例：12:30:30  			  表示 12点30分30秒
    public static final String FORMAT_DATETIME_Y4MDHM = "yyyy-MM-dd HH:mm"; //例：2019-08-16 12:30  	  表示 2019年8月16日 12点30分
    public static final String FORMAT_DATETIME_Y4MDHMS = "yyyy-MM-dd HH:mm:ss";//例：2019-08-16 12:30:30  表示 2019年8月16日 12点30分30秒


    public static LocalDateTime parseLocalDateTime(String param) {
        LocalDateTime parse = LocalDateTime.parse(param, DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
        return parse;
    }

    public static LocalDate parseLocalDate(String param) {
        LocalDate parse = LocalDate.parse(param, DateTimeFormatter.ofPattern(DATE_FORMATTER));
        return parse;
    }

    public static String getDateTimeString(LocalDateTime param) {
        String format = param.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
        return format;
    }

    public static String getDateString(LocalDate param) {
        String format = param.format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
        return format;
    }

    public static boolean validModifiedDateTime(LocalDateTime dbDateTime, LocalDateTime dateTime) {
        if (dbDateTime != null && (!dbDateTime.isEqual(dateTime))) {
            return false;
        }
        return true;
    }

    public static LocalDateTime parseNewLocalDateTime() {
        java.util.Date date = new java.util.Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = localDateTime.toLocalTime();
        return localDateTime;
    }


    /**
     * 计算LocalDateTime时间差，保留两位小数
     * @param start 开始时间
     * @param end 结束时间
     * @return 返回float类型
     */
    public static float lDateTimeDifference(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        long tian = duration.toDays();
        //小时
        long shi = duration.toHours();
        //相差的分钟数
        long fen = duration.toMinutes();
        Float paringHours = null;
        paringHours = Float.valueOf((float) (fen / 60.0));

        if (shi <= 0) {
            paringHours = Float.valueOf(fen);
        }

        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String p = decimalFormat.format(paringHours);
        return Float.parseFloat(p);
    }

    private static String formatTime(Integer minuteStr){

        String resultStr = "";

        if (isEmpty(minuteStr)) {

        }else{
            resultStr = "0时0分";
        }
        return resultStr;
    }
}
