
package com.kunyao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateTimeUtil {

	private static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
	
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YEAR = "yyyy";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String FORMAT_DATA_STRING="yyyyMMddHHmmss";

	public static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat(DATE_TIME_FORMAT);
	public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
	
	
	public static final String formatDateTime(Date date) {
		return DATE_TIME_FORMATTER.format(date);
	}
	
	/**
	 * <h1>格式化日期,并转换为字符串类型</h1>
	 * <ul>
	 * 	   <li>输入日期:new Date()</li>
	 * 	   <li>输出日期:2013-03-13</li>
	 * </ul>
	 * @param date
	 * @return
	 */
	public static final String formatDate(Date date) {
		return DATE_FORMATTER.format(date);
	}
	
	/**
	 * <h1>把给定的字符串日期，转换为日期类型</h1>
	 * <ul>
	 * 	   <li>输入的字符串日期:2012-12-12 12:00:12</li>
	 * 		<li>输出的日期:2012-12-12 12:00:12</li>
	 * </ul>
	 * @param dateTime
	 * @return
	 * @throws ParseException
	 */
	public static final Date parseDateTime(String dateTime) throws ParseException {
		try {
			return DATE_TIME_FORMATTER.parse(dateTime);
		} catch (ParseException e) {
			logger.error("日期转换错误"+e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * <p>
	 * 		<h1>把给定的字符串类型的日期，转换为：2012-12-13 0:00:00</h1>
	 * 		<ul>
	 * 			<li>输入日期:2012-12-12</li>
	 * 			<li>输出日期:2012-12-13 0:00:00</li>
	 * 		</ul>
	 * </p>
	 * @param dateTime
	 * @return
	 * @throws ParseException 
	 */
	public static final Date parseStartDateTime(String dateTime) throws ParseException {
		try {
			Date date = DATE_FORMATTER.parse(dateTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 0);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		} catch (ParseException e) {
			logger.error("日期转换错误"+e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * <p>
	 * 		<h1>把给定的字符串类型的日期，转换为：2012-12-13 0:00:00</h1>
	 * 		<ul>
	 * 			<li>输入日期:2012-12-12</li>
	 * 			<li>输出日期:2012-12-13 23:59:59</li>
	 * 		</ul>
	 * </p>
	 * @param dateTime
	 * @return
	 * @throws ParseException 
	 */
	public static final Date parseEndDateTime(String dateTime) throws ParseException {
		try {
			Date date = DATE_FORMATTER.parse(dateTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);
			calendar.set(Calendar.SECOND, -1);
			return calendar.getTime();
		} catch (ParseException e) {
			logger.error("日期转换错误"+e.getMessage(), e);
			throw e;
		}
	}
}
