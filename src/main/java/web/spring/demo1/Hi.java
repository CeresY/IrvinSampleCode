package web.spring.demo1;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;

public class Hi implements InitializingBean{
	private String msg;
	private Date date;

	public void myInit() {
		this.msg = "Wideens";
		this.date = new Date();
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.msg = "implements Method";
		this.date = new Date();
	}

}
