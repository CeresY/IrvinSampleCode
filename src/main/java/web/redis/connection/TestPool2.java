package web.redis.connection;

import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestPool2 {
	private static String redisConfigFile = "redis-md.properties";
	
	public static void main(String[] args) {
		for(int i=0; i<100; i++) {
			TestPool2 foo = new TestPool2();
			JedisPool pool = foo.initialPool();
			Jedis jedis = pool.getResource();
			String str = jedis.get("key1");
			System.out.println("【输出>>>>】key:" + str + " 第" + i + "个连接");
		}
		
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 初始化Redis连接池
	 */
	public JedisPool initialPool() {
		JedisPool jedisPool = null;
		try {
			Properties props = new Properties();
			// 加载连接池配置文件
			props.load(RedisPoolUtil.class.getClassLoader().getResourceAsStream(redisConfigFile));
			// 创建jedis池配置实例
			JedisPoolConfig config = new JedisPoolConfig();
			// 设置池配置项值
			config.setMaxTotal(Integer.valueOf(props.getProperty("redis.maxTotal")));
			config.setMaxIdle(Integer.valueOf(props.getProperty("redis.maxIdle")));
			config.setMaxWaitMillis(Long.valueOf(props.getProperty("redis.maxWaitMillis")));
			config.setMinEvictableIdleTimeMillis(Long.valueOf(props.getProperty("redis.minEvictableIdleTimeMillis")));
			config.setNumTestsPerEvictionRun(Integer.valueOf(props.getProperty("redis.numTestsPerEvictionRun")));
			config.setTimeBetweenEvictionRunsMillis(Long.valueOf(props.getProperty("redis.timeBetweenEvictionRunsMillis")));
			config.setTestOnBorrow(Boolean.valueOf(props.getProperty("redis.testOnBorrow")));
			config.setTestWhileIdle(Boolean.valueOf(props.getProperty("redis.testWhileIdle")));
			//config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")));
			// 根据配置实例化jedis池
			jedisPool = new JedisPool(config, props.getProperty("redis.host"),
					Integer.valueOf(props.getProperty("redis.port")),
					Integer.valueOf(props.getProperty("redis.timeout")));
			//System.out.println("线程池被成功初始化");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jedisPool;
	}
}
