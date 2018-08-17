package thread.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
  * @author yangzhan
  * @date 2018年8月14日
  */

public class ThreadPoolFactory {
	
	private static ThreadPoolExecutor executor = null;
	
	private ThreadPoolFactory() {
		
	}

	public static ExecutorService getTheadPool() {
		if(executor == null) {
			synchronized (ThreadPoolFactory.class) {
				if(executor==null){
					executor = new ThreadPoolExecutor(10, 20, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(30, false));
                }
			}
		}
		return executor;
	}
}
