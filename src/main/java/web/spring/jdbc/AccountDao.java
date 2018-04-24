package web.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import corejava.model.Account;

/**
  * @author yangzhan
  * 2018年4月23日
  */
@Repository
public class AccountDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addAccount(Account ac) {
		String sql = "insert into sys_account (username, pwd, create_date) values (?, ?, ?)";
		jdbcTemplate.update(sql, ac.getUsername(), ac.getPwd(), ac.getDate());
	}
}
