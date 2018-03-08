package web.spring.demo1;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestHi {
	private static ApplicationContext ctx;
	
	public TestHi() {
		ctx = new FileSystemXmlApplicationContext("webContent/config/spring_v1.xml");
	}
	
	public static void main(String[] args) {
		new TestHi();
		HelloWord hi = (HelloWord)ctx.getBean("hw");
		/*
		Set s = hi.getContainer();
		Iterator it = s.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		*/
		
		Map m = hi.getContainer1();
		Set ks = m.keySet();
		for(Object key : ks) {
			System.out.println(m.get(key));
		}
	}
}
