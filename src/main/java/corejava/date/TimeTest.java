package corejava.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TimeTest {
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Test
	public void test1() {
		Long[] mills = {1535087784788L};
		for(int i=0; i<mills.length; i++) {
			Date date = new Date(mills[i]);
			System.out.println(sdf2.format(date));
		}
	}
	
	@Test
	public void test2() {
		Date getMin = new Date();
		try {
			Date date = sdf.parse("19700101 00:00:00");
			getMin = sdf2.parse("2019-05-06 10:04:03");
			long t = date.getTime();
			System.out.println(date);
			System.out.println(t);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(new Date(-28800000L));
		System.out.println(new Date(-120L));

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getMin);
		int min = calendar.get(Calendar.MINUTE);
		System.out.println("***********分钟："+min);
		System.out.println("***********分钟(余)："+min%3);
	}
	
	@Test
	public void systemTime() {
		for(int i=0; i<10; i++) {
			System.out.println("TimeMillis=" + System.currentTimeMillis()+"\t" + "nanoTime=" + System.nanoTime());
		}
	}
}
