package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;

import corejava.date.DateFormatterUtils;

/** 简单工具类 */
public class SimpleUtils {
	
	public static final String FORMATDATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATDATE = "yyyy-MM-dd";
	public static final String FORMATTIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static java.util.Date getJavaDate(Object o, String format) throws ParseException {
		if(o == null){
			return null;
		}
		return DateFormatterUtils.getDateFromString(String.valueOf(o), format);
	}
	
	public static java.sql.Date getSqlDate(Object o, String format) throws ParseException {
		if(o == null){
			return null;
		}
		java.util.Date date = DateFormatterUtils.getDateFromString(String.valueOf(o), format);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
	
	/**
	 * 字符串转换成DateTime
	 * @param value
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Timestamp getSqlDatetime(Object value, String format) throws ParseException {
		if(value == null){
			return null;
		}
		Date date = org.apache.commons.lang3.time.DateUtils.parseDate(String.valueOf(value), format);
		return new java.sql.Timestamp(date.getTime());
	}
	
	public static java.sql.Timestamp getSqlTimestamp(Object o, String format) throws ParseException {
		if(o == null){
			return null;
		}
		java.util.Date date = DateFormatterUtils.getDateFromString(String.valueOf(o), format);
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(date.getTime());
		return sqlTimestamp;
	}
	
	public static java.sql.Timestamp getSqlTimestamp(String o) throws ParseException {
		if(o == null){
			return null;
		}
		java.util.Date date = new Date(o);
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(date.getTime());
		return sqlTimestamp;
	}
	
	/**
	 * 转换成oracle日期格式 to_date yyyy-mm-dd
	 */
	public static String getOracleToDateSql(Object o) throws ParseException {
		if(o == null){
			return null;
		}
		StringBuffer sb = new StringBuffer(" to_date('");
		String str = DateFormatUtils.format(DateFormatterUtils.getDateFromString(String.valueOf(o),"yyyy-MM-dd"),"yyyy-MM-dd");
		sb.append(str);
		sb.append("','yyyy-mm-dd')");
		return sb.toString();
	}
	
	/**
	 * 转换成oracle日期格式 to_date yyyy-mm-dd hh24:mi:ss
	 */
	public static String getOracleToDateTimeSql(Object o) throws ParseException {
		if(o == null){
			return null;
		}
		StringBuffer sb = new StringBuffer(" to_date('");
		String str = DateFormatUtils.format(DateFormatterUtils.getDateFromString(String.valueOf(o),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
		sb.append(str);
		sb.append("','yyyy-mm-dd hh24:mi:ss')");
		return sb.toString();
	}
	
}
