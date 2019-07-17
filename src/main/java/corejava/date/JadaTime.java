package corejava.date;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-17
 * @Vesion 1.0
 **/
public class JadaTime {
    @Test
    public void test1() {
        LocalDate d1 = LocalDate.now();
        LocalTime now = LocalTime.now();
        System.out.println(d1.toString());
        System.out.println("now = " + now.toString("HH:mm:ss"));
    }
}
