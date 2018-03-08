package web.spring.model;

import java.util.Date;

public class HelloWorld {
	private String msg;
	private Date dateRegist;

	public HelloWorld(String str) {
		this.msg = str;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void test() {
		HelloWorld h = new HelloWorld("");
		System.out.println(h.msg);
	}
}
