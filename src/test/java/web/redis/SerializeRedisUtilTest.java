package web.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Test
	public void testSaveList() {
		Account ac1 = new Account("张三", "zs852", new Date());
		Account ac2 = new Account("李四", "ls352", new Date());
		Account ac3 = new Account("王五", "ww541", new Date());
		String key = "ac-2";
		List<Account> list = new ArrayList<>();
		list.add(ac1);
		list.add(ac2);
		list.add(ac3);
		
		tool.save(key, list, 4);
		
		for(int i=0; i<6; i++) {
			List<Account> rs = tool.get(key, List.class);
			if(rs != null && !rs.isEmpty()) {
				System.out.println(i + "|" + rs.toString());
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
