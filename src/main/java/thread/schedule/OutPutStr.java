package thread.schedule;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OutPutStr {
	// out和out2加了锁，持有锁对象为this，所以N个线程调用out,out2会产生竞争
	public synchronized void out(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));
		}
		System.out.println();
	}
	
	public void out2(String str) {
		synchronized (this) {
			for (int i = 0; i < str.length(); i++) {
				System.out.print(str.charAt(i));
			}
			System.out.println();
		}
	}
	
	 //true表示公平锁，false非公平锁
    private Lock lock = new ReentrantLock();
    
    public void out3(String str) {
        lock.lock();//如果有其它线程已经获取锁，那么当前线程在此等待直到其它线程释放锁。
        try {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i));
            }
            System.out.println();
        } finally {
            lock.unlock();//释放锁资源，之所以加入try{}finally{}代码块，
            //是为了保证锁资源的释放，如果代码发生异常也可以保证锁资源的释放，
            //否则其它线程无法拿到锁资源执行业务逻辑，永远处于等待状态。
        }
    }
}
