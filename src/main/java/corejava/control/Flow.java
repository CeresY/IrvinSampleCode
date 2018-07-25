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
		for(int m=0; m<LEN; m++) {
			System.out.println("M=" + m);
			for(int n=0; n<LEN; n++) {
				System.out.print(n + "\t");
				if(n == 4) {
					break;
				}
			}
			System.out.println();
		}
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
