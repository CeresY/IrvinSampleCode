package web.redis;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
  * @author yangzhan
  * 2018年3月29日
  */
@ContextConfiguration(locations = {"classpath:cache/spring-base.xml", "classpath:cache/spring-redis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTestCase extends AbstractJUnit4SpringContextTests {
	
}
