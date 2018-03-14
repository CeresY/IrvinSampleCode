package corejava.control;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * ��Ҫ����һЩcorejava�����̿������
 * @author yangzhan
 *
 */
public class FlowControl {
	private static int COUNTER = 10;
	
	public static void main(String[] args) {
		objTest();
	}
	
	private static void breakTest() {
		for(int k=0; k<COUNTER; k++) {
			System.out.println("k=" + k);
			for(int j=0; j<COUNTER; j++) {
				System.out.println("inner loop j=" + j);
				break;
			}
			System.out.println("trigger break k=" + k);
		}
	} 
	
	private static void whileTest() {
		int left = 0;
		int right = 0;
		while(true) {
			break;
		}
	}
	
	private static void objTest() {
		List<Integer> list = new ArrayList<>();
		Integer c = null;
		for(int i=0; i<10; i++) {
			//Integer c = new Random().nextInt(17);
			c = new Random().nextInt(17);
			System.out.print(c + " ");
			list.add(c);
		}
		System.out.println();
		for(Integer it : list) {
			System.out.print(it + " ");
		}
		
		LinkedList list1 = new LinkedList<>();
	}

}
