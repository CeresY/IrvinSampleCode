package web.ehcache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import corejava.model.Account;

/**
  * @author yangzhan
  * 2018年3月29日
  */
@Service("ehcacheService")
public class EhcacheServiceImpl implements EhcacheService {

	@Override
	@Cacheable(value="HelloWorldCache", key="#param")
	public long getTimestamp(String param) {
		return System.currentTimeMillis();
	}

	@Override
	@Cacheable(value="HelloWorldCache", key="#key")
	public String getDataFromDB(String key) {
		System.out.println("从数据库获取数据");
		return key + ":" + String.valueOf(Math.round(Math.random()*1000000));
	}

	@Override
	@CacheEvict(value="HelloWorldCache", key="#key")
	public void removeDataAtDB(String key) {
		System.out.println("从数据库中删除数据");
	}

	@Override
	@CachePut(value="HelloWorldCache", key="#key")
	public String refreshData(String key) {
		System.out.println("模拟从数据库中加载数据");
        return key + "::" + String.valueOf(Math.round(Math.random()*1000000));
	}

	// ------------保存对象-----------------
	@Override
	@Cacheable(value="UserCache", key="'user:' + #userId")  
	public Account findById(String userId) {
		System.out.println("模拟从数据库中查询数据");
        return (Account) new Account("1", "mengdee");  
	}

	@Override
	@Cacheable(value="UserCache", condition="#userId.length()<12")   
	public boolean isReserved(String userId) {
		System.out.println("UserCache:"+userId);    
        return false; 
	}

	/**
	 * 清除掉UserCache中某个指定key的缓存 
	 */
	@Override
	@CacheEvict(value="UserCache",key="'user:' + #userId")  
	public void removeUser(String userId) {
		System.out.println("UserCache remove:"+ userId); 
	}

	/**
	 * 清除掉UserCache中全部的缓存 
	 */
	@Override
	@CacheEvict(value="UserCache", allEntries=true)
	public void removeAllUser() {
		System.out.println("UserCache delete all");
	}

}
