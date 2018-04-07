package web.redis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * reids的java工具
 * @author yangzhan-xps13
 * @date 2018年4月8日
 */
public class JedisTest {
	
	private static JedisPool pool;
	private static Jedis jedis = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pool = new JedisPool(new JedisPoolConfig(), "localhost");
		jedis = pool.getResource();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		jedis.close();
		pool.destroy();
	}
	
	@Test
	public void set1() {
		String reVal = jedis.set("xx", "abc");
		System.out.println(reVal);
		String reVal2 = jedis.get("xx");
		System.out.println(reVal2);
	}
}
