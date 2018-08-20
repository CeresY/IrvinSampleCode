package thread.executor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
  * @author yangzhan
  * @date 2018年8月20日
  */
public class Task2 implements Runnable {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 线程私有属性，创建线程时创建
     */
    private Integer num = 0;
 
    public Task2(Integer num) {
        this.num = num;
    }
    
	@Override
	public void run() {
        System.out.println(Thread.currentThread().getName() + "\t" + sdf.format(new Date()) + "\t" + num);
        	//使线程睡眠，模拟线程阻塞情况
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        System.out.println(Thread.currentThread().getName() + "\t" + sdf.format(new Date()) + "\t" + num + "\tend");
	}
}
