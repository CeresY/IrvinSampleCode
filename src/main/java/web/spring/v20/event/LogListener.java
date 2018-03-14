package web.spring.v20.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class LogListener implements ApplicationListener {

	/*@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof LogEvent) {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			System.out.println("���ʱ��:" + currentDate + "���������: " + event.toString());
		}
	}*/

	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof LogEvent) {
			LogEvent logE = (LogEvent) event;
			String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			System.out.println("���ʱ��:" + currentDate + "���������: " + logE.cotent());
		}
	}
}
