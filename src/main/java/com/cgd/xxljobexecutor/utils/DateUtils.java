package com.cgd.xxljobexecutor.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/6/27 17:53
 */
public class DateUtils {

    /**
     * 获取当前格式的日期
     * @param format yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @return 字符串类型的日期
     */
    public static String getStringDate(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String dateStringParse = sdf.format(date);
        return dateStringParse;
    }

    /**
     * 返回日期类时间
     * @param format yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @return 日期类型的时间
     * @throws ParseException
     */
    public static Date getDate(String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String dateString = sdf.format(date);
        Date dateParse = sdf.parse(dateString);
        return date;
    }

    /**
     * 获取当前日期的+day或-day天
     * @param format yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @param day 指定的正负day天
     * @return
     * @throws ParseException
     */
    public static String getDate(String format,int day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        //add方法 参数也可传入 月份，获取的是前几月或后几月的日期
        //calendar1.add(Calendar.MONTH, -3);
        String dateString = sdf.format(calendar.getTime());
        return dateString;
    }

    /**
     * 日期类型转成指定格式的字符串类型
     * @param format
     * @param date
     * @return
     */
    public static String dateParseString(String format,Date date){
        SimpleDateFormat sdf =  new SimpleDateFormat(format);
        String dateString = sdf.format(date);
        return dateString;
    }
}
