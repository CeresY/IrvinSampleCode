package web.redis.connection;

import redis.clients.jedis.Jedis;

public class TestPool3 {

	public static void main(String[] args) {
		for(int i=0; i<2010; i++) {
			Jedis jedis = new Jedis("localhost", 6379);
			String key = "key1";
			String val = jedis.get(key);
			System.out.println("【输出>>>>】"+key+":" + val + " 第" + i + "个连接");
			/*try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
