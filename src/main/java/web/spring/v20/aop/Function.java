package web.spring.v20.aop;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Function {
	private  static ApplicationContext ctx = null;
	private Logger log = Logger.getLogger(this.getClass());
	
	public Function() {
		ctx = new FileSystemXmlApplicationContext("src/config/spring_1.xml");
	}
	
	public static void main(String[] args) {
		new Function().test3();
	}
	
	public void test1() {
		TimeBookInterface timeBook = (TimeBookInterface) ctx.getBean("logAroundProxy");
		timeBook.doAuditing("????");
	}
	
	public void test2() {
		TimeBookInterface timeBook = (TimeBookInterface) ctx.getBean("logAroundProxyCglib");
		timeBook.doAuditing("????");
	}
	
	public void test3() {
		TimeBookInterface timeBook = (TimeBookInterface) ctx.getBean("timeBook");
		timeBook.doCheck("????");
	}
}
