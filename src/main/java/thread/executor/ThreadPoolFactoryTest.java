package thread.executor;

import java.util.concurrent.ExecutorService;

import org.junit.Test;

/**
  * @author yangzhan
  * @date 2018年8月17日
  */
public class ThreadPoolFactoryTest {

	@Test
	public void task() {
		ExecutorService pool = ThreadPoolFactory.getTheadPool();
		for(int i=0; i<30; i++) {
			Task1 task = new Task1();
			pool.execute(task);
		}
		pool.shutdown();
	}
}
