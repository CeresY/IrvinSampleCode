package web.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.model.Account;

public class RedisTemplateUtilTest {
	
	private static AbstractApplicationContext ctx;
	private static RedisTemplateUtil redis;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext("classpath:cache/spring-base.xml", "classpath:cache/spring-redis.xml");
		redis = (RedisTemplateUtil) ctx.getBean("redisTemplateUtil");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ctx.close();
	}
	
	@Test
	public void testString() {
		redis.set("name", "张三");
		redis.set("age", 24);
		redis.set("address", "成都");
        System.out.println(redis.set("address", "北京", 50));
        System.out.println(redis.get("age"));
        
        redis.set("age", 1000);
        System.out.println(redis.get("age"));
	}
	
	@Test
	public void testObject() {
		Account ac = new Account("王五", "ww123");
		redis.set("ww", ac);
		Account acR = (Account) redis.get("ww");
		System.out.println(acR.toString());
	}
	
	@Test
	public void testIncr() {
		redis.set("xx", 1000L);
		System.out.println(redis.get("xx"));
		redis.incr("xx", 3L);
		System.out.println(redis.get("xx"));
	}
	
	@Test
	public void lRemove() {
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("A");
		list.add("D");
		list.add("C");
		String key = "T-list";
		redis.del(key);
		List<Object> objList = new ArrayList<>(list);
		redis.lSetAll(key, objList);
		// 打印
		List<Object> temp1 = redis.lGet(key, 0, -1);
		System.out.println("----------------打印----------------");
		for(int i=0; i<temp1.size(); i++) {
			Object obj = temp1.get(i);
			System.out.println(obj.toString());
		}
		
		redis.lRemove(key, 4, "A");
		List<Object> temp2 = redis.lGet(key, 0, -1);
		System.out.println("----------------打印----------------");
		for(int i=0; i<temp2.size(); i++) {
			Object obj = temp2.get(i);
			System.out.println(obj.toString());
		}
	}
	
}
