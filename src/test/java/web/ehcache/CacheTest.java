package web.ehcache;

import org.junit.Test;

import corejava.model.Account;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

/** ehcache缓存测试
  * @author yangzhan
  * 2018年3月29日
  */
public class CacheTest {

	public static void main(String[] args) {
		System.out.println("haha");
		// 1.创建缓存管理器
		CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache.xml");
		// 2.获取对象
		Cache cache = cacheManager.getCache("HelloWorldCache");
		// 3.创建元素
		Element e = new Element("user:1", "yang");
		Account ac = new Account();
		ac.setPwd("123456");
		ac.setUsername("管理员");
		Element e2 = new Element("user:2", ac);
		// 4.放入元素
		cache.put(e);
		cache.put(e2);
		// 5.获取缓存
		for(int i=0; i<2; i++) {
			Element ee = cache.get("user:1");
			System.out.println(ee);
			System.out.println(ee.getObjectValue());
			
			Element ee2 = cache.get("user:2");
			Account ac2 = (Account) ee2.getObjectValue();
			System.out.println(ee2);
			System.out.println(ac2.getUsername());
			
			// 测试缓存失效时间
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		// 6.删除元素
		// cache.remove("user:1");
		
		// 7.刷新缓存
		cache.flush();
		
		// 8.关闭缓存
		cacheManager.shutdown();
	}
	
	/**
	 * 编程式创建对象
	 */
	@Test
	public void createCache() {
		CacheConfiguration config = new CacheConfiguration();
		config.setName("dyCache");
		config.setMaxEntriesLocalHeap(100);
		config.setEternal(false);
		config.setTimeToIdleSeconds(3);
		config.setTimeToLiveSeconds(3);
		config.setMemoryStoreEvictionPolicy("LRU");
		Cache cache = new Cache(config);
		
		// 没有被add的cache是无效的
		CacheManager manager = CacheManager.create();
		manager.addCache(cache);
		
		// 3.创建元素
		String eKey = "user:1";
		Element e = new Element(eKey, "yang");
		// 4.放入元素
		cache.put(e);
		// 5.获取缓存
		for(int i=0; i<6; i++) {
			Element ee = cache.get(eKey);
			System.out.println(ee);
			//System.out.println(ee.getObjectValue());
			// 测试缓存失效时间
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}


//<!-- 长期不失效 -->
//<cache name="neverCache"
//   maxElementsInMemory="10000"
//   eternal="true"
//   timeToIdleSeconds="0"
//   timeToLiveSeconds="0"
//   overflowToDisk="true"
//   memoryStoreEvictionPolicy="LRU"/>
//   
//<!-- 实时数据 -->
//<cache name="ordinaryCache"
//   maxElementsInMemory="10000"
//   eternal="false"
//   timeToIdleSeconds="3600"
//   timeToLiveSeconds="3600"
//   overflowToDisk="true"
//   memoryStoreEvictionPolicy="LRU"/>