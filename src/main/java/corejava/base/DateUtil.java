package corejava.base;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
  * @author yangzhan
  * 2018年3月19日
  */
public class DateUtil {
		/**
		 * 获取某一周的周一
		 * @param week 第几周
		 * @return 周一格式 yyyy-MM-dd
		 */
		public static void getMonday() {
			String week = "2017W36";
			int year_ = Integer.parseInt(week.substring(0, 4));
			int weekNo_ = Integer.parseInt(week.substring(5, 7));
			Calendar cal = Calendar.getInstance();  
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);        
	        cal.set(Calendar.YEAR, year_); 
	        cal.set(Calendar.WEEK_OF_YEAR, weekNo_);
	        
	        // 格式化
	        String year = String.valueOf(cal.get(Calendar.YEAR));
	        int _month = cal.get(Calendar.MONTH) + 1;
	        String month = _month < 10 ? "0"+_month : String.valueOf(_month);
	        int _day = cal.get(Calendar.DAY_OF_MONTH);
	        String day = _day < 10 ? "0"+_day : String.valueOf(_day);
	        System.out.println(year+"-"+month+"-"+day);
		}
		
		/**
		 * 获取当前日期，本年度最后一天，最后一秒
		 * @return
		 */
		public static Date getLastDayOfYear(Date now) {
			GregorianCalendar gc_now = new GregorianCalendar();
			gc_now.setTime(now);
			GregorianCalendar last_day = new GregorianCalendar(gc_now.get(Calendar.YEAR), 11, 31, 23, 59, 59); // 每年的最后一天，最后一秒
			return last_day.getTime();
		}
		
		/**
		 * 获取当前日期，本年度第一天，第一秒
		 * @return
		 */
		public static Date getFirstDayOfYear(Date now) {
			GregorianCalendar gc_now = new GregorianCalendar();
			gc_now.setTime(now);
			GregorianCalendar last_day = new GregorianCalendar(gc_now.get(Calendar.YEAR), 0, 1, 0, 0, 0); // 本年度第一天，第一秒
			return last_day.getTime();
		}
		
		/**
		 * 获取当前日期，本季度最后一天，最后一秒
		 * @param now
		 * @return
		 */
		public static Date getLastDayOfSeaon(Date now) {
			GregorianCalendar gc_now = new GregorianCalendar();
			gc_now.setTime(now);
			int monthInt = gc_now.get(Calendar.MONTH);
			int maxDay = 31;
			if(monthInt >=0 && monthInt <= 2) {
				monthInt = 2;
			} else if(monthInt >=3 && monthInt <= 5) {
				monthInt = 5;
				maxDay = 30;
			} else if(monthInt >=6 && monthInt <= 8) {
				monthInt = 8;
				maxDay = 30;
			} else if(monthInt >=9 && monthInt <= 11) {
				monthInt = 11;
			}
			GregorianCalendar lastday = new GregorianCalendar(gc_now.get(Calendar.YEAR), monthInt, maxDay, 0, 0, 0);
			return lastday.getTime();
		}
		
		/**
		 * 获取当前日期，本月最后一天，最后一秒
		 * @param now
		 * @return
		 */
		public static Date getLastDayOfMonth(Date now) {
			GregorianCalendar gc_now = new GregorianCalendar();
			gc_now.setTime(now);
			int yearInt = gc_now.get(Calendar.YEAR);
			int monthInt = gc_now.get(Calendar.MONTH);
			int maxDay = 31;
			if(monthInt == 3 || monthInt == 5 || monthInt == 8 || monthInt == 10) {
				maxDay = 30;
			} else if(monthInt == 1) {
				int year = gc_now.get(Calendar.YEAR);
				if(gc_now.isLeapYear(year)) {
					maxDay = 29;
				} else {
					maxDay = 28;
				}
			}
			GregorianCalendar lastday = new GregorianCalendar(yearInt, monthInt, maxDay, 23, 59, 59);
			return lastday.getTime();
		}
		
		/**
		 * 获取当前日期，本月第一天，第一秒
		 * @param now
		 * @return
		 */
		public static Date getFirstDayOfMonth(Date now) {
			GregorianCalendar gc_now = new GregorianCalendar();
			gc_now.setTime(now);
			int yearInt = gc_now.get(Calendar.YEAR);
			int monthInt = gc_now.get(Calendar.MONTH);
			GregorianCalendar firstDay = new GregorianCalendar(yearInt, monthInt, 1, 0, 0, 0);
			return firstDay.getTime();
		}
		
		/**
		 * 获取当前日期，本周最后一天，最后一秒
		 * @param now
		 * @return
		 */
		public static Date getLastDayOfWeek(Date now) {
			GregorianCalendar gc_now = new GregorianCalendar();
			gc_now.setTime(now);
			int dayOfWeekInt = gc_now.get(Calendar.DAY_OF_WEEK);
			GregorianCalendar lastday = null;
			if(dayOfWeekInt == 1) {
				int year = gc_now.get(Calendar.YEAR);
				int monthInt = gc_now.get(Calendar.MONTH);
				int dayInt = gc_now.get(Calendar.DAY_OF_MONTH);
				lastday = new GregorianCalendar(year, monthInt, dayInt, 23, 59, 59);
			} else {
				gc_now.add(Calendar.DAY_OF_YEAR, 8-dayOfWeekInt);
				int year = gc_now.get(Calendar.YEAR);
				int monthInt = gc_now.get(Calendar.MONTH);
				int dayInt = gc_now.get(Calendar.DAY_OF_MONTH);
				lastday = new GregorianCalendar(year, monthInt, dayInt, 23, 59, 59);
			}
			return lastday.getTime();
		}
		
		/**
		 * 获取当前日期，本天最后一秒
		 * @param now
		 * @return
		 */
		public static Date getLastDayOfDay(Date now) {
			GregorianCalendar gc_now = new GregorianCalendar();
			gc_now.setTime(now);
			int year = gc_now.get(Calendar.YEAR);
			int monthInt = gc_now.get(Calendar.MONTH);
			int dayInt = gc_now.get(Calendar.DAY_OF_MONTH);
			GregorianCalendar lastday = new GregorianCalendar(year, monthInt, dayInt, 23, 59, 59);
			return lastday.getTime();
		}
		
		/**
		 * 近7天，时间周期
		 * @return index0:开始时间，index1截止时间
		 */
		public static List<String> getRecent7Days() {
			List<String> scope = new ArrayList<String>();
			Date now = new Date();
			// 开始时间
			GregorianCalendar gc_start = new GregorianCalendar();
			gc_start.setTime(now);
			gc_start.add(Calendar.DAY_OF_YEAR, -7);
			GregorianCalendar initStartDate = 
					new GregorianCalendar(gc_start.get(Calendar.YEAR), gc_start.get(Calendar.MONTH), gc_start.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			Date startDate = initStartDate.getTime();
			String startStr = DateFormatUtils.format(startDate,"yyyy-MM-dd HH:mm:ss");
			scope.add(startStr);
			// 结束时间
			GregorianCalendar gc_end = new GregorianCalendar();
			gc_end.setTime(now);
			GregorianCalendar initEndDate = 
					new GregorianCalendar(gc_end.get(Calendar.YEAR), gc_end.get(Calendar.MONTH), gc_end.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			Date endDate = initEndDate.getTime();
			String endStr = DateFormatUtils.format(endDate,"yyyy-MM-dd HH:mm:ss");
			scope.add(endStr);
			return scope;
		}
		
		/**
		 * 近30天，时间周期
		 * @return index0:开始时间，index1截止时间
		 */
		public static List<String> getRecent30Days() {
			List<String> scope = new ArrayList<String>();
			Date now = new Date();
			// 开始时间
			GregorianCalendar gc_start = new GregorianCalendar();
			gc_start.setTime(now);
			gc_start.add(Calendar.DAY_OF_YEAR, -30);
			GregorianCalendar initStartDate = 
					new GregorianCalendar(gc_start.get(Calendar.YEAR), gc_start.get(Calendar.MONTH), gc_start.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			Date startDate = initStartDate.getTime();
			String startStr = DateFormatUtils.format(startDate,"yyyy-MM-dd HH:mm:ss");
			scope.add(startStr);
			// 结束时间
			GregorianCalendar gc_end = new GregorianCalendar();
			gc_end.setTime(now);
			GregorianCalendar initEndDate = 
					new GregorianCalendar(gc_end.get(Calendar.YEAR), gc_end.get(Calendar.MONTH), gc_end.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			Date endDate = initEndDate.getTime();
			String endStr = DateFormatUtils.format(endDate,"yyyy-MM-dd HH:mm:ss");
			scope.add(endStr);
			return scope;
		}
		
		/**
		 * 当月，时间周期
		 * @return index0:开始时间，index1截止时间
		 */
		public static List<String> getRecentMonth() {
			List<String> scope = new ArrayList<String>();
			Date now = new Date();
			// 开始时间
			Date startDate = getFirstDayOfMonth(now);
			String startStr = DateFormatUtils.format(startDate,"yyyy-MM-dd HH:mm:ss");
			scope.add(startStr);
			// 结束时间
			GregorianCalendar gc_end = new GregorianCalendar();
			gc_end.setTime(now);
			GregorianCalendar initEndDate = 
					new GregorianCalendar(gc_end.get(Calendar.YEAR), gc_end.get(Calendar.MONTH), gc_end.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			Date endDate = initEndDate.getTime();
			String endStr = DateFormatUtils.format(endDate,"yyyy-MM-dd HH:mm:ss");
			scope.add(endStr);
			return scope;
		}
		
		/**
		 * 获取当前时间的季度字符串，如：2017Q01
		 * @param gc
		 * @return
		 */
		public static String getSeasonStr(GregorianCalendar gc) {
			if(gc != null) {
				int yearInt = gc.get(Calendar.YEAR);
				int monthInt = gc.get(Calendar.MONTH);
				String season = "";
				if(monthInt >=0 && monthInt <= 2) {
					season = "1";
				} else if(monthInt >=3 && monthInt <= 5) {
					season = "2";
				} else if(monthInt >=6 && monthInt <= 8) {
					season = "3";
				} else if(monthInt >=9 && monthInt <= 11) {
					season = "4";
				}
				return String.valueOf(yearInt)+"Q"+season;
			}
			return null;
		}
		
		/**
		 * 获取当前时间的月度字符串，如：201712
		 * @param gc
		 * @return
		 */
		public static String getMonthStr(GregorianCalendar gc) {
			if(gc != null) {
				int yearInt = gc.get(Calendar.YEAR);
				int monthInt = gc.get(Calendar.MONTH)+1;
				return monthInt < 10 ? String.valueOf(yearInt)+"0"+String.valueOf(monthInt) :
						String.valueOf(yearInt)+String.valueOf(monthInt);
			}
			return null;
		}
		
		/**
		 * 获取当前时间的"周"字符串，如：2017W01
		 * @param gc
		 * @return
		 */
		public static String getWeekStr(GregorianCalendar gc) {
			if(gc != null) {
				int yearInt = gc.get(Calendar.YEAR);
				int weekInt = gc.get(Calendar.WEEK_OF_YEAR);
				return weekInt < 10 ? String.valueOf(yearInt)+"W0"+String.valueOf(weekInt) : 
					String.valueOf(yearInt)+"W"+String.valueOf(weekInt);
			}
			return null;
		}
	}















