package corejava.control;

import org.junit.Test;

public class Flow {
	static int LEN = 20;
	@Test
	public void testIf() {
		Integer i = 0;
		if(i != null) {
			if(i == 0) {
				System.out.println("i=0");
			}
		} else {
			System.out.println("i is null");
			return;
		}
		
		System.out.println("i=" + i);
	}
	
	@Test
	public void breakArea() {
		outterLoop: for(int i=0; i<5; i++) {
			System.out.println(i);
			for(int j=0; j<5; j++) {
				System.out.print(j + "\t");
				if(j == 3) {
					break outterLoop;
				}
			}
			System.out.println("\n-----------------------\n");
		}
		System.out.println("~end");
	} 
	
	@Test
	public void breakWhile() {
		int i = 10;
		while(i-->0) {
			if(i == 5) {
				break;
			}
			System.out.println(i);
		}
	}
	
	@Test
	public void testWhile() {
		int i=0;
		while(i++>-1) {
			System.out.println(i);
			if(i == 5) {
				break;
			}
		}
	}
}
