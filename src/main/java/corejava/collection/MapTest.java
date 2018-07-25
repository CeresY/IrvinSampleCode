package corejava.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import corejava.model.Account;
import corejava.model.Student;

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
	
	@Test
	public void addMultiElements() {
		Map<String,String> map = new HashMap<>();
		map.put("1", "A");
		map.put("2", "B");
		map.put("3", "C");
		map.put("4", "D");
		Map<String,String> map2 = new HashMap<>();
		map2.put("1", "A");
		map2.put("2", "BB");
		map2.put("5", "E");
		map2.put("6", "F");
		map.putAll(map2);
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + "=" + map.get(key));
		}
	}
	
	public <T> void compare(Class<T> clazz) throws InstantiationException, IllegalAccessException {
		if(clazz.newInstance() instanceof Student) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}
	
	@Test
	public void test3() {
		try {
			compare(Account.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
