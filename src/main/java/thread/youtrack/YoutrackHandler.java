package thread.youtrack;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class YoutrackHandler {
	private volatile boolean isRunning = true;
	private volatile boolean isRunningC = true;
	private static final int CAPACITY = 4;
	private final ExecutorService threadPool = Executors.newFixedThreadPool(3);
	private final LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<String>(CAPACITY);
	
	//请求线程
	class RequestTask implements Callable<String> {
		@Override
		public String call() throws Exception {
			
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		YoutrackHandler foo = new YoutrackHandler();
		Producer p1 = foo.new Producer(foo.getDeque());
		Consumer c1 = foo.new Consumer(foo.getDeque());
		Consumer c2 = foo.new Consumer(foo.getDeque());
		foo.getThreadPool().execute(p1);
		foo.getThreadPool().execute(c1);
		foo.getThreadPool().execute(c2);
		foo.getThreadPool().shutdown();
		System.out.println("==============end====================");
	}
	
	public ExecutorService getThreadPool() {
		return threadPool;
	}

	public LinkedBlockingDeque<String> getDeque() {
		return deque;
	}

	class Producer implements Runnable {
		private BlockingDeque<String> deque;
		public Producer(BlockingDeque<String> deque) {
			this.deque = deque;
		}

		@Override
		public void run() {
			while (isRunning) {
				for(int i=0; ; i++) {
					String response = RequestUtil.requestUserList(i);
					if(response == null) {
						this.stop();
						//Thread.currentThread().interrupt();
						break;
					} else {
						List<String> list = RequestUtil.getUserList(response);
						for(int j=0; j<list.size(); j++) {
							try {
								deque.put(list.get(j));
								System.out.println(Thread.currentThread().getName() + " " + list.get(j));
							} catch (InterruptedException e) {
								e.printStackTrace();
								Thread.currentThread().interrupt();
							}
						}
					}
				}
			}
		}
		
		public synchronized void stop () {
			isRunning = false;
			System.out.println("关闭P");
		}
	}
	
	class Consumer implements Runnable{
		private BlockingDeque<String> deque;
		public Consumer(BlockingDeque<String> deque) {
			this.deque = deque;
		}

		public void run() {
			System.out.println(Thread.currentThread().getName() + "******" + deque.isEmpty());
				while(isRunningC) {
					if(!isRunning) {
						if(deque.isEmpty()) {
							this.stop();
							//Thread.currentThread().interrupt();
							break;
						}
					}
					try {
						String loginname = deque.take();
						if(loginname != null) {
							String response = RequestUtil.requestUserDetail(loginname);
							String detail = RequestUtil.getUserDetail(response);
							System.out.println("【】"+Thread.currentThread().getName() + ": " + detail);
						}
						//System.out.println("【d】" + Thread.currentThread().getName() + ": " + deque.isEmpty());
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}
		}
		
		public synchronized void stop () {
			isRunningC = false;
			System.out.println("关闭C");
		}
	}
}
