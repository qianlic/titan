package com.cjwx.titan.engine.util;

import com.cjwx.titan.engine.core.exception.ServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 日前操作工具类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public abstract class DateUtils {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * 获取当前时间
     */
    public static Date now() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 判断字符串是否为日期格式, 如: 2016-12-12
     */
    public static boolean isDate(String string) {
        return StringUtils.isNotEmpty(string) && string.matches(DATE_PATTERN);
    }

    /**
     * 时间转字符串, 格式:yyyy-MM-dd HH:mm:ss
     */
    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * 时间转字符串
     */
    public static String dateToString(Date date, String pattern) {
        if (ObjectUtils.isEmpty(date) || StringUtils.isEmpty(pattern)) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 字符串转时间, 格式:yyyy-MM-dd HH:mm:ss
     */
    public static Date stringToDate(String string) {
        return stringToDate(string, DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * 字符串转时间
     */
    public static Date stringToDate(String string, String pattern) {
        if (StringUtils.isEmpty(string) || StringUtils.isEmpty(pattern)) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(string);
        } catch (ParseException e) {
            throw new ServiceException("转换成日期格式 [" + string + "," + pattern + "] 失败", e);
        }
    }

    /**
     * 获取当前所属年
     */
    public static int currentYear() {
        return calendar().get(Calendar.YEAR);
    }

    /**
     * 获取当前所属月
     */
    public static int currentMonth() {
        return calendar().get(Calendar.MONTH);
    }

    /**
     * 获取当前所属周
     */
    public static int currentWeek() {
        return calendar().get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前所属天
     */
    public static int currentDate() {
        return calendar().get(Calendar.DATE);
    }

    /**
     * 获取时间操作日历
     */
    public static Calendar calendar() {
        return Calendar.getInstance();
    }

}
