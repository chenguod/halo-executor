package com.cgd.xxljobexecutor.utils;

import java.text.DateFormat;
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
     * 返回日期类时间
     *
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
        return dateParse;
    }

    /**
     * 日期类型转成指定格式的字符串类型
     *
     * @param format
     * @param date
     * @return
     */
    public static String dateParseString(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(date);
        return dateString;
    }

    /**
     * 获取当前日期指定格式的字符串类型的日期返回类型
     *
     * @param format yyyy-MM-dd或yyyyMMdd或yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String nowTime(String format) {
        Date date = new Date();
        DateFormat format1 = new SimpleDateFormat(format);
        String time = format1.format(date);
        return time;
    }

    /**
     * 取指定格式、指定日期的字符串时间值
     *
     * @param o
     * @param format 日期格式 yyyy-MM-dd或yyyyMMdd或yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String nowTime(Object o, String format) {
        Date date = new Date((Long) o);
        DateFormat format1 = new SimpleDateFormat(format);
        String time = format1.format(date);
        return time;
    }

    /**
     * 获取当前日期的+day或-day天
     *
     * @param amount 间隔日期
     * @param format yyyy-MM-dd或yyyyMMdd或yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String nowDate(int amount, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, amount);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String Date = sdf.format(calendar.getTime());
        return Date;
    }

    public static String getMonthFirst(String format){
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format1.format(c.getTime());
        return first;
    }

    public static String getMonthLast(String format){
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format1.format(ca.getTime());
        return last;
    }

}
