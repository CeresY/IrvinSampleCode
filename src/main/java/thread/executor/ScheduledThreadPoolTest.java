package thread.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** 单任务定时任务
  * @author yangzhan
  * @date 2018年8月20日
  */
public class ScheduledThreadPoolTest implements Runnable{
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void run() {
		System.out.println("task1 start\t"+sdf.format(new Date()));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("task1 end(3s)\t"+sdf.format(new Date()));
	}
	
	public static void main(String[] args) {
		ScheduledThreadPoolTest t1 = new ScheduledThreadPoolTest();
		
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
		//立即执行t1，3s后任务结束，再等待2s（间隔时间-消耗时间），如果有空余线程时，再次执行该任务
		//pool.scheduleAtFixedRate(t1, 0, 5, TimeUnit.SECONDS);
		
		//立即执行t1，3s后任务结束，再等待5s（间隔时间），如果有空余线程时，再次执行该任务
		pool.scheduleWithFixedDelay(t1, 0, 5, TimeUnit.SECONDS);
	}
}