package web.redis;

import corejava.model.Account;

/**
  * @author yangzhan
  * 2018年4月4日
  */
public interface IRedisService {
	public Account getAccountByName(String userName);
}

