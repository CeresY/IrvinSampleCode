package thread.copy;

public class DaemonThread implements Runnable {

	protected Thread[] lookupThreads;
	protected int threadCount;
	
	public DaemonThread(String threadName, int threadNum) {
		//super(threadName);
		threadCount = threadNum;
		lookupThreads = new Thread[threadNum];
	}
	
	public void process() {
		for(int i=0; i<threadCount; i++) {
			lookupThreads[i] = new Thread(this);
			lookupThreads[i].start();
		}
	}

	@Override
	public void run() {
		int lock = 10;
		while(--lock > 0) {
			int i = LogicT.swap(lock);
			System.out.println(Thread.currentThread().getName() + " " + lock);
		}
	}
	
}
