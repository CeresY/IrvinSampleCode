package web.ehcache;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
  * @author yangzhan
  * 2018年4月2日
  */
public class UnitTest {

	public static void main(String[] args) {
		AbstractApplicationContext ctx =  new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
		Cache cache = (Cache) ctx.getBean("cache2");
		Element e = new Element("1", "val2");
		cache.put(e);
		for(int i=0; i<6; i++) {
			String val = String.valueOf(cache.get("1"));
			System.out.println(val);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		ctx.close();
	}
}
