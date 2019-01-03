package corejava.collection;

import java.util.HashMap;
import java.util.HashSet;
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
		
		Map<String, Integer> map2 = new HashMap<>();
		map2.put("1", 1);
		System.out.println(map2.get("1"));
		System.out.println(map2.get("2"));
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
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
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
	
	@Test
	public void testSetMap() {
		Set<Map<String,String>> set = new HashSet<>();
		
		Map<String,String> map1 = new HashMap<>();
		map1.put("A", "A");
		set.add(map1);
		
		Map<String,String> map2 = new HashMap<>();
		map2.put("A", "B");
		set.add(map2);
		
		Map<String,String> map3 = new HashMap<>();
		map3.put("C", "B");
		set.add(map3);
		
		Map<String,String> map4 = new HashMap<>();
		map4.put("A", "B");
		set.add(map4);
		
		Iterator<Map<String,String>> it = set.iterator();
		while(it.hasNext()) {
			Map<String,String> map = it.next();
			Set<String> keys = map.keySet();
			Iterator<String> itMap = keys.iterator();
			while(itMap.hasNext()) {
				String key = itMap.next();
				System.out.println(key + "=" + map.get(key));
			}
			System.out.println("--------------");
		}
	}
}




