
package com.alqsoft.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* *
 *类名：UtilDate
 *功能：自定义订单类
 *详细：工具类，可以用作获取系统日期、订单编号等
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class UtilDate {
	private static Log logger = LogFactory.getLog(UtilDate.class);
    /** 年月日时分秒毫秒(无下划线) yyyyMMddHHmmssSSS */
    public static final String dtOrder                  = "yyyyMMddHHmmssSSS";
    
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日 yyyy-MM-dd*/
    public static final String nyrSimple                  = "yyyy-MM-dd";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
	
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtTime                 = "HHmmss";
    
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmssSSS为格式的当前系统时间
     */
	public synchronized static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtOrder);
		return df.format(date);
	}
	/**
     * 返回系统当前时间(精确到秒)
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public static String getDateLong(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/* public static void main(String[] args) {
		System.out.println(getOrderNum()+"|"+getThree());
	} */
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	/**
	 * 获取系统当前日期(年月日)，格式：yyyy-MM-dd
	 * @return
	 */
	public  static String getNyrDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(nyrSimple);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		ThreadLocalRandom rad=ThreadLocalRandom.current();
		int num=rad.nextInt(100,999);
		return num+"";
	}
	
	public  static String getDateFormatter(Date date){
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	/**
	 * String转Date
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date getString2Date(String str) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
		return formatter.parse(str);
	}
	/**
	  * 得到本周周一
	  *
	  * @return yyyy-MM-dd
	  */
	 public static String getMondayOfThisWeek() {
	  Calendar c = Calendar.getInstance();
	  SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 1);
	  return df2.format(c.getTime());
	 }
	 

	 /**
	  * 得到本周周日
	  *
	  * @return yyyy-MM-dd
	  */
	 public static String getSundayOfThisWeek() {
	  SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar c = Calendar.getInstance();
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 7);
	  return df2.format(c.getTime());
	 }
	 /**
	  * 得到此日期周一
	  * @param date
	  * @return	yyyy-MM-dd
	  */
	 public static String getMondayOfDate(Date date) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  SimpleDateFormat df2 = new SimpleDateFormat(nyrSimple);
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 1);
	  return df2.format(c.getTime());
	 }
	 

	 /**
	  * 得到此日期周日
	  * @param date
	  * @return yyyy-MM-dd
	  */
	 public static String getSundayOfDate(Date date) {
	  SimpleDateFormat df2 = new SimpleDateFormat(nyrSimple);
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 7);
	  return df2.format(c.getTime());
	 }
	 /**
	  * 得到当前时间后一周的时间点
	  * @author Yaowei
	  * @param  
	  * @return Date
	  * @Time 2017年3月29日
	  */
	 public static  Date getAfterweekOfDate(){
		
		//当前时间  
        Date now = new Date();  
        System.out.println("现在时间："+now.toLocaleString());  
        Calendar calendar = new GregorianCalendar();  
        //将Date设置到Calendar中  
        calendar.setTime(now);  
        //获得当前时间之后一周时间点  
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 7);  
        Date date=calendar.getTime();
        return date;
	 }
}
