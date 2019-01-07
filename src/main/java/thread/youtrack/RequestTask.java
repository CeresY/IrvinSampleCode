package thread.youtrack;

import java.util.concurrent.Callable;

/**
 * 请求线程
 * @date 2019年1月8日
 */
public class RequestTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		return null;
	}
	
	public static void main(String[] args) {
		int total = 50, size = 10;
		int loop = total%size > 0 ? total/size+1 : total/size;
		System.out.println(loop);
	}

}
