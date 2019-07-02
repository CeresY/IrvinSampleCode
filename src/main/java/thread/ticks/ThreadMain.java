package thread.ticks;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadMain implements Runnable{
	private volatile int count;
	private volatile AtomicLong lg = new AtomicLong();
	
	private int getInt() {
		return ++count;
	}
	
	/*private synchronized int getInt() {
		return ++count;
	}*/

	private long getLong() {
		return lg.incrementAndGet();
	}
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			int run = getInt();
//			long run = getLong();
//			int run = ++count;
			if(run <= 20){
				try {
					Thread.sleep(1000*1);
				} catch (InterruptedException e) {
					e.printStackTrace();
					throw new IllegalArgumentException();
				}
				try {
					Thread.currentThread().wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"执行: "+run);
			} else {
				Thread.currentThread().interrupt();
				System.out.println(Thread.currentThread().getName() + "will die: " + run );
			}
		} 
	}

}
