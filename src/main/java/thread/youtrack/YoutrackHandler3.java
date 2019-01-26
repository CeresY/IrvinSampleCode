package thread.youtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
  * @date 2019年1月8日
  */
public class YoutrackHandler3 {
	private static final int CAPACITY = 10;
	private final ExecutorService threadPool = Executors.newFixedThreadPool(CAPACITY);
	private final BlockingQueue<String> requestQueue = new LinkedBlockingQueue<String>();
	private final static int count = 100, size=40;
	
	// 使用单线程处理
	public static void main1(String[] args) {
		long start = System.currentTimeMillis();
		List<String> endList = new ArrayList<>();
		List<String> requestList = new ArrayList<>();
		for(int i=0; i<count; i++) {
			requestList.add(String.valueOf(i));
		}
		//处理请求
		for(String request : requestList) {
			String response = RequestUtil.requestUserDetail(request);
			endList.add(response);
		}
		
		System.out.println("打印解析内容-");
		for(String str : endList) {
			System.out.println(RequestUtil.getUserDetail(str));
		}
		System.out.println("用时:" + (System.currentTimeMillis()-start));
	}
	
	// 使用多线程处理
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		YoutrackHandler3 foo = new YoutrackHandler3();
		for(int i=0; i<count; i++) {
			try {
				foo.getRequestQueue().put(String.valueOf(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		int total = foo.getRequestQueue().size();
		int loop = total%size > 0 ? total/size+1 : total/size;
		System.out.println("loop="+loop);
		
		List<Callable<List<String>>> list = new ArrayList<>();
		for(int i=0; i<loop; i++) {
			RequestFun fun = foo.new RequestFun(foo.getRequestQueue());
			list.add(fun);
		}
		System.out.println("----------");
		List<String> endList = new ArrayList<>();
		
		try {
			List<Future<List<String>>> futures = foo.getThreadPool().invokeAll(list, 1000*60*5, TimeUnit.MILLISECONDS);
			for(Future<List<String>> future : futures) {
				List<String> response = future.get();
				endList.addAll(response);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			foo.getThreadPool().shutdown();;
		}
		
		System.out.println("打印解析内容-");
		for(String str : endList) {
			System.out.println(RequestUtil.getUserDetail(str));
		}
		System.out.println("用时:" + (System.currentTimeMillis()-start));
	}

	// 请求用户详情
	class RequestFun implements Callable<List<String>> {
		private BlockingQueue<String> requestQueue;

		public RequestFun(BlockingQueue<String> request) {
			this.requestQueue = request;
		}

		@Override
		public List<String> call() throws Exception {
			System.out.println(Thread.currentThread().getName());
			List<String> result = new ArrayList<>();
			while(!Thread.currentThread().isInterrupted()) {
				//System.out.println("*: "+Thread.currentThread().getName());
				if(requestQueue.isEmpty()) {
					Thread.currentThread().interrupt();
					System.out.println("关闭线程: "+Thread.currentThread().getName());
				} else {
					String id = requestQueue.take();
					String response = RequestUtil.requestUserDetail(id);
					if(response != null) {
						result.add(response);
					}
					System.out.println("#: "+Thread.currentThread().getName());
				}
			}
			System.out.println("【end】"+Thread.currentThread().getName());
			return result;
		}
		/*public List<String> call() throws Exception {
			System.out.println(Thread.currentThread().getName());
			List<String> result = new ArrayList<>();
			try {
				while(!requestQueue.isEmpty()) {
					String id = requestQueue.take();
					String response = RequestUtil.requestUserDetail(id);
					if(response != null) {
						result.add(response);
					}
				}
				System.out.println("【end】"+Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}*/
	}

	public ExecutorService getThreadPool() {
		return threadPool;
	}

	public BlockingQueue<String> getRequestQueue() {
		return requestQueue;
	}
	
}
