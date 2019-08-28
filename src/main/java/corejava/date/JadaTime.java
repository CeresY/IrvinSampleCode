package corejava.date;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description // https://blog.csdn.net/psh18513234633/article/details/79408096
 * @Author yz
 * @Date 2019-7-17
 * @Vesion 1.0
 **/
public class JadaTime {
    static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    static DateTimeFormatter ymdhm = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    static DateTimeFormatter ymd = DateTimeFormat.forPattern("yyyy-MM-dd");
    static DateTimeFormatter hm = DateTimeFormat.forPattern("HH:mm");
    static DateTimeFormatter hms = DateTimeFormat.forPattern("HH:mm:ss");

    @Test
    public void testDateTime() {

        Set<String> keys = new HashSet<>();
        keys.add("a");
        keys.add("b");
        keys.add("c");
        String[] a = new String[keys.size()];
        a = keys.toArray(a);
        for(String s : a) {
            System.out.println(": "+ s);
        }

        System.out.println();
        if(false && 1>2) {
            System.out.println("false,false");
        } else {
            System.out.println("ok");
        }

        System.out.println(LocalDateTime.now()+"\n");

        DateTime dt = DateTime.now();
        System.out.println("property-1: " + dt.monthOfYear().getAsText());
        System.out.println("property-2: " + dt.monthOfYear().withMaximumValue());
        System.out.println("property-3: " + dt.monthOfYear().roundCeilingCopy());
        System.out.println("property-3: " + dt.secondOfMinute().withMinimumValue()+"\n");

        System.out.println("dt.toString: " + dt.toString()+"\n");

        DateTime dt2 = DateTime.parse("2016-05-30 23:04:59", formatter);
        System.out.println("parse: " + dt2.toString(formatter));
        System.out.println("parse-Hm: " + dt2.toString(hm)+"\n");

        String now3 = DateTime.now().toString(formatter);
        System.out.println("now3: "+now3+"\n");

        Date d1 = DateTime.now().toDate();
        System.out.println("d1: "+d1);
        System.out.println("dt2: " + DateTime.now().withDayOfMonth(3).toString(formatter));
        System.out.println("dt3: " + DateTime.now().withHourOfDay(4).toString(formatter));
        System.out.println("dt4: " + DateTime.now().withTimeAtStartOfDay().toString(formatter)+"\n");

        // 下面的代码将计算五年前的第二个月的最后一天
        DateTime now = DateTime.now();
        DateTime then = now.minusYears(5) // five years ago
                .monthOfYear()     // get monthOfYear property
                .setCopy(2)        // set it to February
                .dayOfMonth()      // get dayOfMonth property
                .withMaximumValue();// the last day of the month
        System.out.println("五年前的第二个月的最后一天: " + then.toString(formatter));
        System.out.println("五年前的第二个月的最后一天: " + then.toString("yyyy-MM-dd HH:mm:ss")+"\n");

        DateTime dateTime = DateTime.now();
        System.out.println("Format1: " + dateTime.toString(ISODateTimeFormat.basicDateTime()));
        System.out.println("Format2: " + dateTime.toString(ISODateTimeFormat.basicDateTimeNoMillis()));
        System.out.println("Format3: " + dateTime.toString(ISODateTimeFormat.basicOrdinalDateTime()));
        System.out.println("Format4: " + dateTime.toString(ISODateTimeFormat.basicWeekDateTime()));
    }

    @Test
    public void testLocalDate() {
        LocalDate day = LocalDate.now().plusDays(5);
        int dayIndx = DateTimeConstants.SUNDAY;
        System.out.println(day.toString() + "\t周几： " + day.getDayOfWeek());
        // System.out.println(LocalDate.now().toString("yyyyMMdd"));
        // System.out.println(LocalDate.now().toString() + " " + LocalTime.now().toString("HH:mm:ss"));

        LocalDate start = new LocalDate();
        LocalDate end = new LocalDate().minusDays(1);
        System.out.println("day1: " + Days.daysBetween(start, end).getDays());
    }

    @Test
    public void testDatetime2() {
        DateTime dt = DateTime.now().plusDays(1);
        System.out.println("withHourOfDay: " + dt.withHourOfDay(1).toString(formatter));
        System.out.println("P: " + dt.withMillisOfDay(0).toString(formatter));

        System.out.println("\r\n");
        DateTime start = DateTime.now().plusDays(1).withMillisOfDay(0);
        DateTime end = DateTime.now().plusDays(2).withMillisOfDay(0);

        String str = "2019-08-27 13:12:59".substring(11,16);
        System.out.println("["+str+"]");

        DateTime open = DateTime.parse("2019-08-27 13:12:59", formatter);
        System.out.println(start.toString(formatter)+"\t开始:" + open.compareTo(start));
        System.out.println(end.toString(formatter)+"\t结束:" + open.compareTo(end));
    }

    @Test
    public void testLocaTime() {
        LocalTime time = LocalTime.now();
        String hhmm = time.toString("HH:mm");
        System.out.println("HH:mm=[" + hhmm+"]");

        System.out.println("\r\nvalue0=["+time.getValue(0)+"]");
        System.out.println("value1=["+time.getValue(1)+"]");
        System.out.println("value2=["+time.getValue(2)+"]");
        System.out.println("value3=["+time.getValue(3)+"]");
    }

    @Test
    public void temp() {
        /*DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        String openTime = "2018-08-27 17:12:20";
        openTime = openTime.substring(0,16);
        System.out.println("len="+openTime.length()+": "+openTime);
        DateTime open = DateTime.parse(openTime, formatter);*/

        /*DateTime start = DateTime.now().plusDays(1).withMillisOfDay(0);
        DateTime end = DateTime.now().plusDays(2).withMillisOfDay(0);

        DateTime now = DateTime.now().plusDays(1);
        System.out.println("now.compareTo(start): " + now.compareTo(start));
        System.out.println("now.compareTo(end): " + now.compareTo(end));

        System.out.println("start: " + start.toString(formatter));
        System.out.println("end: " + end.toString(formatter));
        System.out.println("now: " + now.toString(formatter));*/

        /*long open = 1535447540000L;
        long star = 1566921600000L;
        Date openD = new Date(open);
        Date starD = new Date(star);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("openD: "+sdf.format(openD));
        System.out.println("startD: "+sdf.format(starD));*/

        // DateTime dt = DateTime.now();
        // System.out.println(dt.toString(hm));

        LocalDate d1 = LocalDate.now();
        System.out.println("\nLocalDate: " + d1.toString(ymd));

        DateTime star =d1.toDateTimeAtStartOfDay();
        System.out.println("\nStartOfDay: " + star.toString(formatter));

        DateTime current = d1.toDateTimeAtCurrentTime();
        System.out.println("\nCurrentTime: " + current.toString(formatter));

        LocalTime t1 = LocalTime.fromMillisOfDay(0);
        System.out.println("\nMillisOfDay: " + t1.toString(hms));

        DateTime dt3 = DateTime.parse("2019-09-30 23:20:59", formatter);
        System.out.println("\n" + dt3.toString());

        System.out.println("\nwithMinimumValue: "+DateTime.now().secondOfMinute().withMinimumValue().toString(formatter));
    }

}
