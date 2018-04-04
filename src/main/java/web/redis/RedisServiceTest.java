package web.redis;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import corejava.model.Account;


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
		String userName = "xi";
		log.info("第一次查询");
		Account ac = redisService.getAccountByName(userName);
		log.info("first: " + ac.toString());
		log.info("第二次查询");
		Account ac2 = redisService.getAccountByName(userName);
		log.info("second: " + ac2.toString());
	}
	
	@Test
	public void testUpdateAccount1() {
		String userName = "xi";
		Account ac = redisService.getAccountByName(userName);
		log.info("查询结果：" + ac.toString());
		ac = new Account("xi", "1111111");
		redisService.updateAccount1(ac);
		ac = redisService.getAccountByName(userName);
		log.info("查询结果：" + ac.toString());
	}
}
