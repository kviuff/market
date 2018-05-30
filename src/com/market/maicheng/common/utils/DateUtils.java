package com.market.maicheng.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;


/**
 * 日期工具类
 * @author Snake
 *
 */
public class DateUtils {
	private static final String FORMAT_DATE = "yyyy-MM-dd";
	private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	//private static final  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	public static String getLongToDate(Long millSec){
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
	    Date date= new Date(millSec);
	    return sdf.format(date);
	}
	
	public static String getLongToDateTime(Long millSec){
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_TIME);
	    Date date= new Date(millSec);
	    return sdf.format(date);
	}
	
	/**
	 * 获取时间字符串
	 * @param date
	 * @return
	 */
	public static String parseDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_TIME);
		return sdf.format(date);
	}
	
	/**
	 * 根据时间字符串和具体格式转换成Date类型
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseString(String dateStr,String format) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 根据字符串转换成Date类型 yyyy-MM-dd格式
	 * @param dateStr
	 * @return
	 */
	public static Date parseDateByString(String dateStr){
		return parseString(dateStr, FORMAT_DATE);
	}
	/**
	 * 根据字符串转换成Date类型 yyyy-MM-dd HH:mm:ss格式
	 * @param dateStr
	 * @return
	 */
	public static Date parseDateTimeByString(String dateStr){
		return parseString(dateStr, FORMAT_DATE_TIME);
	}
	
	public static Date parseDateByYearMonthDay(int year, int month, int day){
		String str = year + "-" + month +"-" + day;
		return parseDateByString(str);
	}
	
	/**
	 * 格式化Date到字符串
	 * @param date
	 * @param format 
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if(date == null || format == null || "".equals(format)){
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	public static String formatToDate(Date date){
		return formatDate(date, FORMAT_DATE);
	}
	public static String formatToDateTime(Date date){
		return formatDate(date, FORMAT_DATE_TIME);
	}
	
	/**
	 * 计算2个时间之间的分钟
	 * @param beforedate
	 * @param afterdate
	 * @return
	 */
	
	public static long getDateNum(long before, long after) {
		long quot = 0;
		quot = before - after;
		quot = quot / (1000*60) ;
		return quot;
	}

	/**
	 * 把传进来的时间减 second 秒钟 然后返回
	 * 86400秒是一天
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date getAfterTime(Date date,int second) {
		if(date == null){
			return null;
		}
		Calendar dateTime = Calendar.getInstance();
		dateTime.setTime(date);
		dateTime.set(Calendar.SECOND,dateTime.get(Calendar.SECOND) - second);
		return dateTime.getTime();
	}
	
	
	/**
     * long 转为 日期
     *
     * @param time
     * @return
     */
    public static String formatLongToStr(long time, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = FORMAT_DATE_TIME;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(time);
        String sDateTime = sdf.format(date);
        return sDateTime;
    }
	
	public static void main(String[] a){
		System.out.println(DateUtils.getLongToDateTime(20150424103816L));;
	}
}
