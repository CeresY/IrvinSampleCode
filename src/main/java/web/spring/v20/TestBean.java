package web.spring.v20;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import web.spring.v20.event.Log;

public class TestBean {
	private  static ApplicationContext ctx = null;
	private Logger log = Logger.getLogger(this.getClass());
	
	public TestBean() {
		ctx = new FileSystemXmlApplicationContext("src/config/spring_1.xml");
	}

	public static void main(String[] args) {
		new TestBean().test2();
	}
	
	public void test2() {
		log.info("测试一下效果");
		Log log = (Log)ctx.getBean("log");
		log.pushLog("hi Lily");
	}
	
	public static void test1() {
		HellWorld1 hw = (HellWorld1)ctx.getBean("hw1");
		System.out.println(hw.getDt1() + " " + hw.getMsg());
	}
}
