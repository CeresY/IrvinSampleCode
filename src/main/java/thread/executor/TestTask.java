package thread.executor;

import java.util.concurrent.ExecutorService;

import org.junit.Test;

/**
  * @author yangzhan
  * @date 2018年8月17日
  */
public class TestTask {

	@Test
	public void task() {
		ExecutorService pool = ThreadPoolFactory.getTheadPool();
		for(int i=0; i<30; i++) {
			TaskModel task = new TaskModel();
			pool.execute(task);
		}
		pool.shutdown();
	}
}
