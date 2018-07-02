package corejava.collection;

import org.junit.Test;

public class InitArray {
	@Test
	public void test1() {
		int[] i1 = {1,1,2};
		int[] i2 = new int[]{1,2,3,4};
		int[] i3 = new int[6];
		for(int i : i3) {
			System.out.println(i);
		}
	}
}
