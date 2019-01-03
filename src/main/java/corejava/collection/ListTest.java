package corejava.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;


public class ListTest {
	
	/**
	 * 找出重复内容
	 */
	@Test
	public void findDuplicate() {
		List<String> list = new ArrayList<String>();
		for(int i=0; i<30; i++) {
			list.add(this.getRandom(91));
		}
		// 去重前
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");
		}
		System.out.println(list.size());
		
		List<String> result = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			String previous = list.get(i);
			for(int j=i+1; j<list.size(); j++) {
				String next = list.get(j);
				if(previous.equals(next)) {
					//System.out.println("有重复：" + next);
					result.add(next);
				}
			}
		}
		
		System.out.println(result.toString());
	}
	
	@Test
	public void contain() {
		String key = "44";
		List<String> roleIdList = new ArrayList<String>();
		roleIdList.add("4");
		roleIdList.add("41");
		roleIdList.add("42");
		roleIdList.add("43");
		if(roleIdList.contains(key)) {
			System.out.println("找到了");
		} else {
			System.out.println("没有");
		}
	}
	
	
	/**
	 * 去重
	 * @param dataModelList
	 */
	@Test
	public void distinctObject() {
		List<String> list = new ArrayList<String>();
		for(int i=0; i<30; i++) {
			list.add(this.getRandom(91));
		}
		// 去重前
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");
		}
		System.out.println(list.size());
		
		// 重复坐标
		Set<Integer> indexs = new HashSet<Integer>();
		for(int i=0; i<list.size(); i++) {
			String id1 = list.get(i);
			for(int j=i+1; j<list.size(); j++) {
				String id2 = list.get(j);
				if(id1.equals(id2)) {
					indexs.add(j);
				}
			}
		}
		// 删除重复数据
		List<String> listAfter = new ArrayList<String>();
		for(int i=0; i<list.size(); i++) {
			if(!indexs.contains(i)) {
				listAfter.add(list.get(i));
			}
		}
		
		// 去重后
		for(int i=0; i<listAfter.size(); i++) {
			System.out.print(listAfter.get(i)+", ");
		}
		System.out.println(listAfter.size());
	}
	
	@Test
	public void readNullList() {
		List<String> list = new ArrayList<String>();
		for(String str : list) {
			System.out.println(str);
		}
	}
	
	@Test
	public void remove() {
		List<String> list = new ArrayList<String>();
		for(int i=0; i<10; i++) {
			char c = (char)(97+i);
			System.out.println(c);
			list.add(String.valueOf(c));
		}
		/*for(int i=0; i<list.size(); i++) {
			System.out.println(i + " " +list.get(i));
			list.remove(list.get(i));
		}*/
		for(int i=0; i<list.size(); i++) {
			System.out.println(i + " " +list.get(i));
		}
	}
	
	@Test
	public void delElement() {
		int i=10;
		Set<String> sets = new HashSet<String>();
		while(i-->0) {
			char c = (char)(i+65);
			sets.add(String.valueOf(c));
		}
		Iterator<String> it = sets.iterator();
		while(it.hasNext()) {
			String str = it.next();
			if("A".equals(str) || "D".equals(str) || "F".equals(str) || "I".equals(str)) {
				it.remove();//删除str元素
			}
		}
		Iterator<String> it_ = sets.iterator();
		while(it_.hasNext()) {
			System.out.println(it_.next());
		}
	}
	
	@Test
	public void delList() {
		List<String> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			list.add(i+"H");
		}
		
		System.out.println("删除前");
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + "\t");
		}
		
		System.out.println();
		System.out.println("删除后");
		for(int i=0; i<list.size(); i++) {
			if(i == 2 || i == 5) {
				list.remove(i);
			}
		}
		
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i) + "\t");
		}
		
	}
	
	public String getRandom(int upper) {
		int i = new Random().nextInt(upper);
		if(i<65) {
			return getRandom(upper);
		} else {
			return String.valueOf((char)i);
		}
		
	}
	
	@Test
	public void testRandom() {
		for(int i=0; i<10; i++) {
			System.out.println(this.getRandom(91));
		}
	}
}
