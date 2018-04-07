package web.redis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.model.Account;

public class RedisUtilTest {
	
	private static AbstractApplicationContext ctx;
	private static RedisUtil redisUtil;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new ClassPathXmlApplicationContext("classpath:cache/spring-redis.xml");
		redisUtil = (RedisUtil) ctx.getBean("redisUtil");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ctx.close();
	}
	
	@Test
	public void testString() {
		redisUtil.set("name", "张三");
        redisUtil.set("age", 24);
        redisUtil.set("address", "成都");
        System.out.println(redisUtil.set("address", "北京", 50));
        System.out.println(redisUtil.get("age"));
        
        redisUtil.set("age", 1000);
        System.out.println(redisUtil.get("age"));
	}
	
	@Test
	public void testObject() {
		Account ac = new Account("王五", "ww123");
		redisUtil.set("ww", ac);
		Account acR = (Account) redisUtil.get("ww");
		System.out.println(acR.toString());
	}
	
	@Test
	public void testIncr() {
		redisUtil.set("xx", 1000L);
		System.out.println(redisUtil.get("xx"));
		redisUtil.incr("xx", 3L);
		System.out.println(redisUtil.get("xx"));
	}
	
}
