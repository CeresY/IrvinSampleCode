package corejava.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * 日期处理
 * @author yangzhan-xps13
 * @date 2017年4月25日-上午10:41:09
 */
public class DateTest {
	private static SimpleDateFormat sdf_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 格式化13位时间戳
	 */
	@Test
	public void formate13() {
		long t1 = System.currentTimeMillis();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long t2 = System.currentTimeMillis();
		System.out.println(t1);
		System.out.println(t2);
		System.out.println("t2-t1=" +(t2-t1));
		String value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t1);
		System.out.println(value);
	}
	
	/**
	 * 获取当月最后一天和第一天
	 * @throws ParseException 
	 */
	@Test
	public void lastDay() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = sdf.parse("2017-07-01");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		Date firstDayOfMonth = calendar.getTime();  
		System.out.println(sdf.format(firstDayOfMonth));
		
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDayOfMonth = calendar.getTime();
		System.out.println(sdf.format(lastDayOfMonth));
	}
	
	/**
	 * 星期几
	 * @throws ParseException 
	 */
	@Test
	public void dayOfWeek() throws ParseException {
		Date date = sdf_date.parse("2017-01-01");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(day == 1 ? 7 : day-1);
	}
	
	/**
	 * 某周的周一是几号
	 * @param week
	 * @return
	 */
	@Test
	public void getMonday() {
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
	 * 系统时间
	 */
	@Test
	public void systemDate() {
		System.out.println("Millis: " + System.currentTimeMillis());
		System.out.println("Nanotime: " + System.nanoTime());
	}
	
	/**
	 * 日期加减
	 */
	@Test
	public void plusMinus() {
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(new Date()); 
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//今天的日期 
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);//让日期加1  
		System.out.println(calendar.get(Calendar.DATE));//加1之后的日期Top 
		// 加一天
		Date plusDate = calendar.getTime();
		System.out.println(plusDate);
		
		System.out.println("-----------GC日历--------------");
		GregorianCalendar gc = new GregorianCalendar();
		System.out.println(gc.getTime());
		System.out.println("WEEK_OF_MONTH=" + gc.WEEK_OF_MONTH + "\tWEEK_OF_YEAR=" + gc.WEEK_OF_YEAR);
		gc.roll(Calendar.DAY_OF_WEEK, false);
		System.out.println("DAY_OF_WEEK up=false: " + gc.getTime());
		
		System.out.println("--------------set-gc---------");
		GregorianCalendar set_gc = new GregorianCalendar(2017, Calendar.FEBRUARY, 31);
		System.out.println(set_gc.getTime());
	}
	
	/**
	 * 比较日期大小
	 * @throws Exception
	 */
	@Test
	public void compareTo() throws Exception {
		Date d1 = sdf_time.parse("2017-12-31 23:59:59");
		Date d2 = sdf_time.parse("2017-11-31 23:59:59");
		System.out.println("d1=" + d1);
		System.out.println("d2=" + d2);
		System.out.println("d1.compareTo.d2=" + d1.compareTo(d2));
	}
	
	@Test
	public void gcCompareTo() throws Exception{
		Date start = sdf_date.parse("2017-04-26");
		
		GregorianCalendar gc_start = new GregorianCalendar();
		gc_start.setTime(start);
		System.out.println("开始时间：" + gc_start.getTime());
		
		gc_start.add(Calendar.MONTH, 1);
		System.out.println("更新时间：" + gc_start.getTime());
		
		System.out.println("update.compareTo.start=" + gc_start.getTime().compareTo(start));
		
		
	}
	
	/**
	 * 格利高利日历日历相加减
	 * @throws Exception
	 */
	@Test
	public void plusMinus2() throws Exception{
		// Date start = sdf_date.parse("2013-01-10");
		Date start = sdf_date.parse("2017-04-26");
		Date end = sdf_date.parse("2017-12-31");
		
		GregorianCalendar gc_start = new GregorianCalendar();
		gc_start.setTime(start);
		System.out.println("开始时间：" + gc_start.getTime());
		GregorianCalendar gc_end = new GregorianCalendar();
		gc_end.setTime(end);
		System.out.println("结束时间：" + gc_end.getTime());
		
		if(start.compareTo(end) == 1) { // 指标开始填报时间大于今天
			return;
		}
		
		switch (3) {
		case 0:
			// 年，加减
			System.out.println("------------年，加减------------");
			while(true) {
				Date lastday = this.getLastDayOfYear(gc_start.getTime());
				if(lastday.compareTo(end) == 1) {
					break;
				} else {
					System.out.println(lastday);
				}
				gc_start.roll(Calendar.YEAR, true);
			}
			break;
		case 1 :
			// 季，加减
			System.out.println("------------季，加减------------");
			while(true) {
				// 根据当前日期计算该季度最后一天
				Date lastday = this.getLastDayOfSeaon(gc_start.getTime());
				if(lastday.compareTo(end) == 1) {
					break;
				} else {
					System.out.println(lastday);
				}
				gc_start.add(Calendar.MONTH, 3);
			}
			break;
		case 2 :
			// 月，加减
			System.out.println("------------月，加减------------");
			while(true) {
				Date lastday = this.getLastDayOfMonth(gc_start.getTime());
				if(lastday.compareTo(end) == 1) {
					break;
				} else {
					System.out.println(lastday);
				}
				gc_start.add(Calendar.MONTH, 1);
			}
			break;
		case 3 : 
			// 周，加减
			System.out.println("------------周，加减------------");
			while(true) {
				Date lastday = this.getLastDayOfWeek(gc_start.getTime());
				if(lastday.compareTo(end) == 1) {
					break;
				} else {
					System.out.println(lastday);
				}
				gc_start.add(Calendar.DAY_OF_YEAR, 7);
			}
			break;
		case 4 :
			// 天，加减
			System.out.println("------------天，加减------------");
			while(true) {
				Date lastday = this.getLastDayOfDay(gc_start.getTime());
				if(lastday.compareTo(end) == 1) {
					break;
				} else {
					System.out.println(lastday);
				}
				gc_start.add(Calendar.DAY_OF_YEAR, 1);
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * 获取当前日期，本年度最后一天，最后一秒
	 * @return
	 */
	public Date getLastDayOfYear(Date now) {
		GregorianCalendar gc_now = new GregorianCalendar();
		gc_now.setTime(now);
		GregorianCalendar last_day = new GregorianCalendar(gc_now.get(Calendar.YEAR), 11, 31, 23, 59, 59); // 每年的最后一天，最后一秒
		return last_day.getTime();
	}
	
	/**
	 * 获取当前日期，本季度最后一天，最后一秒
	 * @param now
	 * @return
	 */
	public Date getLastDayOfSeaon(Date now) {
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
		GregorianCalendar lastday = new GregorianCalendar(gc_now.get(Calendar.YEAR), monthInt, maxDay, 23, 59, 59);
		return lastday.getTime();
	}
	
	/**
	 * 获取当前日期，本月最后一天，最后一秒
	 * @param now
	 * @return
	 */
	public Date getLastDayOfMonth(Date now) {
		GregorianCalendar gc_now = new GregorianCalendar();
		gc_now.setTime(now);
		int year = gc_now.get(Calendar.YEAR);
		int monthInt = gc_now.get(Calendar.MONTH);
		int maxDay = 31;
		// 判断大小月
		if(monthInt == 3 || monthInt == 5 || monthInt == 8 || monthInt == 10) {
			maxDay = 30;
		}
		// 判断闰月
		else if(monthInt == 1) {
			if(gc_now.isLeapYear(year)) {
				maxDay = 29;
			} else {
				maxDay = 28;
			}
		}
		System.out.println("\t" + year + "\t" + monthInt + "\t" + maxDay);
		GregorianCalendar lastday = new GregorianCalendar(year, monthInt, maxDay, 23, 59, 59);
		return lastday.getTime();
	}
	
	@Test
	public void test2() {
		Date d;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-25");
			System.out.println(getFirstDayOfWeek(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取当前日期，本周最后一天，最后一秒
	 * @param now
	 * @return
	 */
	public Date getLastDayOfWeek(Date now) {
		GregorianCalendar gc_now = new GregorianCalendar();
		gc_now.setTime(now);
		int dayOfWeekInt = gc_now.get(Calendar.DAY_OF_WEEK);
		GregorianCalendar lastday = null;
		if(dayOfWeekInt == 1) { // 周日是第一天
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
	 * 获取当前日期，本周最第一天，第一秒
	 * @param now
	 * @return
	 */
	public Date getFirstDayOfWeek(Date now) {
		GregorianCalendar gc_now = new GregorianCalendar();
		gc_now.setTime(now);
		int dayOfWeekInt = gc_now.get(Calendar.DAY_OF_WEEK);
		GregorianCalendar lastday = null;
		if(dayOfWeekInt == 2) { // 周一是第2天
			int year = gc_now.get(Calendar.YEAR);
			int monthInt = gc_now.get(Calendar.MONTH);
			int dayInt = gc_now.get(Calendar.DAY_OF_MONTH);
			lastday = new GregorianCalendar(year, monthInt, dayInt, 0, 0, 0);
		} else {
			int days = 0;
			switch (dayOfWeekInt) {
				case 1: days = -6; break;
				case 3: days = -1; break;
				case 4: days = -2; break;
				case 5: days = -3; break;
				case 6: days = -4; break;
				case 7: days = -5; break;
				default:
					break;
			}
			gc_now.add(Calendar.DAY_OF_YEAR, days);
			int year = gc_now.get(Calendar.YEAR);
			int monthInt = gc_now.get(Calendar.MONTH);
			int dayInt = gc_now.get(Calendar.DAY_OF_MONTH);
			lastday = new GregorianCalendar(year, monthInt, dayInt, 0, 0, 0);
		}
		return lastday.getTime();
	}
	
	/**
	 * 获取当前日期，本天最后一秒
	 * @param now
	 * @return
	 */
	public Date getLastDayOfDay(Date now) {
		GregorianCalendar gc_now = new GregorianCalendar();
		gc_now.setTime(now);
		int year = gc_now.get(Calendar.YEAR);
		int monthInt = gc_now.get(Calendar.MONTH);
		int dayInt = gc_now.get(Calendar.DAY_OF_MONTH);
		GregorianCalendar lastday = new GregorianCalendar(year, monthInt, dayInt, 23, 59, 59);
		return lastday.getTime();
	}
	
	/**
	 * 格利高利日历，系统常量
	 */
	@Test
	public void gcSystem() throws Exception {
		// Calendar cal = Calendar.getInstance();
		// System.out.println(cal.getTime() + " | cal = " + cal.getWeeksInWeekYear() + "\t" + cal.getWeekYear());
		GregorianCalendar now = new GregorianCalendar();
		Date d1 = sdf_time.parse("2017-12-31 23:59:59");
		now.setTime(d1);
		System.out.println(now.getTime());
		System.out.println(now.get(Calendar.YEAR) +"\t"+ now.get(Calendar.MONTH) +"\t"+ now.get(Calendar.DAY_OF_MONTH)+"\t");
		GregorianCalendar initStartDate = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		System.out.println(initStartDate.getTime());
	}
	
	/**
	 * 日期[加法]
	 */
	@Test
	public void add() {
		Date startDate = new Date();
		System.out.println(sdf_time.format(startDate));
		GregorianCalendar gc_now = new GregorianCalendar();
		gc_now.setTime(startDate);
		gc_now.add(Calendar.MINUTE, 90);
		
		Date newDate = gc_now.getTime();
		System.out.println(sdf_time.format(newDate));
	}
	
}
