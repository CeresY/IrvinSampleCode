package thread.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** 多线程任务调度
  * @author yangzhan
  * @date 2018年8月20日
  */
public class ScheduledThreadPoolTest2 implements Runnable{
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"\tstart\t"+sdf.format(new Date()));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"\tend(5s)\t"+sdf.format(new Date()));
	}
	
	public static void main(String[] args) {
		ScheduledThreadPoolTest2 t1 = new ScheduledThreadPoolTest2();
		ScheduledThreadPoolTest2 t2 = new ScheduledThreadPoolTest2();
		
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);//启用2个线程
		
		// 立即执行，任务消耗3秒，执行结束后等待2秒，【有空余线程时】，再次执行该任务
		pool.scheduleWithFixedDelay(t1, 0, 2, TimeUnit.SECONDS);
		
		// 立即执行，任务消耗5秒，执行结束后等待2秒，【有空余线程时】，再次执行该任务
		pool.scheduleWithFixedDelay(t2, 0, 2, TimeUnit.SECONDS);
	}
}