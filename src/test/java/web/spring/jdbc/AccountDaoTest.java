package web.spring.jdbc;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import corejava.model.Account;

/**
  * @author yangzhan
  * 2018年4月23日
  */
public class AccountDaoTest extends JdbcTestCase{

	@Autowired
	private AccountDao acDao;
	
	@Test
	public  void testAddAccount() {
		Account ac = new Account("xxx002", "rkhs45", new Date());
		acDao.addAccount(ac);
		System.out.println("保存完成");
	}
}
