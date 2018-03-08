package corejava.base;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 集合类测试
 * @author yangzhan-xps13
 * @date 2017年4月26日-下午3:35:54
 */
public class CollectionsTest {
	@Test
	public void listRemove() {
		List<Integer> rList = new ArrayList<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<10; i++) {
			list.add(i);
		}
		int[] oneArr = {3, 7, 1};
		for(int i=0; i<oneArr.length; i++) {
			int tmp = oneArr[i];
			for(int j=0; j<list.size(); j++) {
				if(tmp == list.get(j)) {
					rList.add(list.get(j));
					list.remove(j);
				}
			}
		}
		rList.addAll(list);
		for(int e : rList){
			System.out.println(e);
		}
	}
}
