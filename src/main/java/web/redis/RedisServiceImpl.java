package web.redis;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import corejava.model.Account;

/**
  * @author yangzhan
  * 2018年4月4日
  */
@Service("redisService")
public class RedisServiceImpl implements IRedisService {
	private Logger log = Logger.getLogger(RedisServiceImpl.class);

	@Override
	@Cacheable(value="commonCache", key="#userName")// 使用了一个缓存名叫 accountCache
	public Account getAccountByName(String userName) {
	    // 方法内部实现不考虑缓存逻辑，直接实现业务
	    log.info("正在查询 Account："+userName);
	    return getFromDB(userName);
	}
	
	private Account getFromDB(String acctName) {
		log.info("正在查询 db..."+acctName);
	    Account ac = new Account("yang", "abc123");
	    return ac;
	}

	@Override
	@CacheEvict(value="commonCache", key="#account.getUsername()")
	public void updateAccount1(Account account) {
		log.info("正在更新 Account："+ account.toString());
		this.updateDB(account);
	}
	private void updateDB(Account account) {
		log.info("正在更新 db..." + account.toString());
	}

}

/*
2.6,spring cache详细讲解
缓存注解有以下三个：
@Cacheable @CacheEvict @CachePut

@Cacheable(value=”accountCache”)，这个注释的意思是，当调用这个方法的时候，会从一个名叫 accountCache 的缓存中查询，
如果没有，则执行实际的方法（即查询数据库），并将执行的结果存入缓存中，否则返回缓存中的对象。
这里的缓存中的 key 就是参数 userName，value 就是 Account 对象。“accountCache”缓存是在 spring*.xml 中定义的名称。
*/