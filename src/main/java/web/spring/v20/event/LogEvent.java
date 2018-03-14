package web.spring.v20.event;

import org.springframework.context.ApplicationEvent;

public class LogEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public LogEvent(Object source) {
		super(source);
	}
	
	public String cotent() {
		return "LogEvent complate";
	}
}
