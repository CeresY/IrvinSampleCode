package util.log;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogService {
    public static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);

    // static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(LogLevel level, String desc) {
        sl4jLog(level, desc);
        print(desc);
    }

    /** 通过slf4j输出 */
    public static void sl4jLog(LogLevel level, String desc) {
        switch (level) {
            case error:
                LOGGER.error(desc);
                break;
            case warn:
                LOGGER.warn(desc);
                break;
            case info:
                LOGGER.info(desc);
            default:
                break;
        }
    }

    public static void print(String desc) {
        printPossible(desc);
    }

    public static void printPossible(String desc) {
        // String dateTime2 = DateTime.now().toString(formatter);
        String dateTime = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime + " | " + desc);
    }
}
