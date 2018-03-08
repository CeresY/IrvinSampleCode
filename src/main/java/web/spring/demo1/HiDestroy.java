package web.spring.demo1;

import java.util.Date;

public class HiDestroy {
	String msg;
	Date date;
	
	public void initM() {
		this.msg = "January";
		this.date = new Date();
	}
	
	public void desM() {
		this.msg = null;
		this.date = null;
		System.out.println("already is destroy`");
	}
}
