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
        System.out.println(LocalDate.now().toString() + " " + LocalTime.now().toString("HH:mm:ss"));
    }
}
