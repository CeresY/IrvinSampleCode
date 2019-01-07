package thread.youtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class YoutrackHandler2 {
	private final ExecutorService threadPool = Executors.newCachedThreadPool();
	private List<String> list = new ArrayList<String>();		
	
	//请求线程
	class RequestTask implements Callable<List<String>> {
		private final List<String> list;
		private Integer start;
		private final Integer end;
		public RequestTask(List<String> list, int start, int end) {
			this.list = list;
			this.start = start;
			this.end = end;
		}

		@Override
		public List<String> call() throws Exception {
			List<String> list2 = new ArrayList<>();
			for(;start<=end; start++) {
				list2.add(RequestUtil.getUserDetail(list.get(start)));
			}
			return list2;
		}
		
	}
	
	public static void main(String[] args) {
		YoutrackHandler2 foo = new YoutrackHandler2();
		for(int i=0; i<51; i++) {
			foo.getList().add("yz-"+String.valueOf(i));
		}
		
		List<FutureTask<List<String>>> fl = new ArrayList<>();
		int total = foo.getList().size(), size = 10;
		int loop = total%size > 0 ? total/size+1 : total/size;
		System.out.println("loop="+loop);
		for(int i=0; i<loop; i++) {
			int start = i*size;
			int end = (i+1)*size-1 > total ? total-1 : (i+1)*size-1;
			System.out.println("start="+start + ",end="+end);
			RequestTask task = foo.new RequestTask(foo.getList(), start, end);
			FutureTask<List<String>> futureTask = new FutureTask<>(task);
			foo.getThreadPool().submit(futureTask);
			fl.add(futureTask);
		}
		foo.getThreadPool().shutdown();
		
		for(FutureTask<List<String>> temp : fl) {
			try {
				List<String> listTemp = temp.get();
				for(String obj : listTemp) {
					System.out.println(obj);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("==============end====================");
	}
	
	public ExecutorService getThreadPool() {
		return threadPool;
	}

	public List<String> getList() {
		return list;
	}
}
