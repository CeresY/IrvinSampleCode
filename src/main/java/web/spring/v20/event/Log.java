package web.spring.v20.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Log implements ApplicationContextAware {
	private ApplicationContext ctx;
	
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}
	
	public int pushLog(String log) {
		LogEvent event = new LogEvent(log);
		ctx.publishEvent(event);
		return 0;
	}
}
