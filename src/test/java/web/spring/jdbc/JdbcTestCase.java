package web.spring.jdbc;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
  * @author yangzhan
  * 2018年3月29日
  */
@ContextConfiguration(locations = {"classpath:application-mysql.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcTestCase extends AbstractJUnit4SpringContextTests {
	
}
