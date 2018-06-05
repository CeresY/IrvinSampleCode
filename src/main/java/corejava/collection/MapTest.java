package corejava.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MapTest {
	@Test
	public void getNoExsisKey() {
		Map<String, String> map = new HashMap<String, String>();
		System.out.println(map.get("key"));
	}
	
	@Test
	public void delElements() {
		Map<String,String> map = new HashMap<>();
		for(int i=0; i<10; i++) {
			String key = String.valueOf(i);
			String val = String.valueOf((char)(i+65));
			map.put(key, val);
		}
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + "=" + map.get(key));
		}
		
		// 删除
		map.remove("5");
		map.remove("8");
		
		System.out.println("---------------------");
		Set<String> set2 = map.keySet();
		Iterator<String> it2 = set2.iterator();
		while(it2.hasNext()) {
			String key = it2.next();
			System.out.println(key + "=" + map.get(key));
		}
	}
}
