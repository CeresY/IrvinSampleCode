package thread.executor;

import java.util.Random;

/**
 * @author yangzhan
 * @date 2018年8月17日
 */
public class Task1 implements Runnable {
	private Integer max = 5000, min = 500;

	@Override
	public void run() {
		int r = new Random().nextInt(max - min + 1) + min;
		System.out.println(Thread.currentThread() + "正在处理");
		try {
			Thread.sleep(r);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread() + "正在完成");
	}

}
