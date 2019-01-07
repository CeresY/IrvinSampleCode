package thread.youtrack;

import java.util.ArrayList;
import java.util.List;

public class RequestUtil {

	/**
	 * 模拟用户列表请求
	 * @return
	 */
	public static String requestUserList(Integer index) {
		
		if(index > 5) {
			return null;
		}
		
		String str = "idx"+index;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 模拟用户列表返回数据解析
	 * @return
	 */
	public static List<String> getUserList(String response) {
		List<String> list = new ArrayList<>();
		for(int i=0; i<4; i++) {
			String str = response+"-"+i;
			list.add(str);
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 模拟用户详情请求
	 * @return
	 */
	public static String requestUserDetail(String loginname) {
		String str = "周"+loginname;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 模拟用户详情请求
	 * @return
	 */
	public static String getUserDetail(String response) {
		String str = "姓名："+response;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return str;
	}
}
