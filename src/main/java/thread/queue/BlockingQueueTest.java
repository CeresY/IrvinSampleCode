package thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列
 * @author yangzhan-xps13
 * 2017年11月21日
 */
public class BlockingQueueTest {
	/**
	 * 生产者
	 * @throws InterruptedException 
	 */
	public void produce() throws InterruptedException {
		BlockingQueue<String> basket = new LinkedBlockingQueue<String>(3);
		basket.put("1");
	}
}
