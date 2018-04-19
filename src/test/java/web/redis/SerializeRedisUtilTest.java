package web.redis;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import corejava.model.Account;

/**
  * @author yangzhan
  * 2018年4月19日
  */
public class SerializeRedisUtilTest extends RedisTestCase {
	@Autowired
	private SerializeRedisUtil tool;
	
	@Test
	public void testSave() {
		Account ac = new Account("张三", "zs852", new Date());
		String key = "ac-1";
		tool.save(key, ac, 4);
		
		for(int i=0; i<6; i++) {
			Account rs = tool.get(key, Account.class);
			if(rs != null) {
				System.out.println(i + "|" +rs.toString());
			} else {
				System.out.println(i + "|");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
