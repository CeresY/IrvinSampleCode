package web.ehcache;

import corejava.model.Account;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/** ehcache缓存测试
  * @author yangzhan
  * 2018年3月29日
  */
public class CacheTest {

	public static void main(String[] args) {
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
	public void createCache() {
		
	}
}
