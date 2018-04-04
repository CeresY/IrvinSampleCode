package web.redis;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
  * @author yangzhan
  * 2018年4月4日
  */
public class RedisServiceTest extends RedisTestCase {
	private Logger log = Logger.getLogger(RedisServiceTest.class);

	@Autowired
	private IRedisService redisService;

	@Test
	public void testGetAccountByName() {
		log.info("第一次查询");
		redisService.getAccountByName("xi");
		log.info("第二次查询");
		redisService.getAccountByName("xi");
	}
}
