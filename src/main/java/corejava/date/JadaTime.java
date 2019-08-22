package corejava.date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description // https://blog.csdn.net/psh18513234633/article/details/79408096
 * @Author yz
 * @Date 2019-7-17
 * @Vesion 1.0
 **/
public class JadaTime {
    static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testDateTime() {
        System.out.println(LocalDateTime.now());

        DateTime dt = new DateTime();
        System.out.println("dt-property-1: " + dt.monthOfYear().getAsText());
        System.out.println("dt-property-2: " + dt.monthOfYear().withMaximumValue());
        System.out.println("dt-property-3: " + dt.monthOfYear().roundCeilingCopy());
        System.out.println(dt.toString());

        String now3 = DateTime.now().toString(formatter);
        System.out.println("now3: "+now3);

        Date d1 = DateTime.now().toDate();
        System.out.println("d1: "+d1);

        System.out.println("dt2: " + DateTime.now().withDayOfMonth(3).toString(formatter));
        System.out.println("dt3: " + DateTime.now().withHourOfDay(4).toString(formatter));
        System.out.println("dt4: " + DateTime.now().withTimeAtStartOfDay().toString(formatter));

        // 下面的代码将计算五年前的第二个月的最后一天
        DateTime now = DateTime.now();
        DateTime then = now.minusYears(5) // five years ago
                .monthOfYear()     // get monthOfYear property
                .setCopy(2)        // set it to February
                .dayOfMonth()      // get dayOfMonth property
                .withMaximumValue();// the last day of the month
        System.out.println("五年前的第二个月的最后一天: " + then.toString(formatter));
        System.out.println("五年前的第二个月的最后一天: " + then.toString("yyyy-MM-dd HH:mm:ss"));

        System.out.println("");
        DateTime dateTime = DateTime.now();
        System.out.println("Format1: " + dateTime.toString(ISODateTimeFormat.basicDateTime()));
        System.out.println("Format2: " + dateTime.toString(ISODateTimeFormat.basicDateTimeNoMillis()));
        System.out.println("Format3: " + dateTime.toString(ISODateTimeFormat.basicOrdinalDateTime()));
        System.out.println("Format4: " + dateTime.toString(ISODateTimeFormat.basicWeekDateTime()));
    }

    @Test
    public void testLocalDate() {
        System.out.println(LocalDate.now().toString() + " " + LocalTime.now().toString("HH:mm:ss"));

        LocalDate start = new LocalDate();
        LocalDate end = new LocalDate().minusDays(1);
        System.out.println("day1: " + Days.daysBetween(start, end).getDays());
    }


}
