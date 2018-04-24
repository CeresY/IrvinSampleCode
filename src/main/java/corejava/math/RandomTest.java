package corejava.math;

import java.util.Random;

import org.junit.Test;

public class RandomTest {
	@Test
	public void test1() {
		for(int i=0; i<30; i++) {
			//System.out.print(new Random().nextInt(10));
			System.out.println(new Random().nextInt(1000));
		}
	}
	
	@Test
	public void time() {
		for(int i=0; i<30; i++) {
			String nano = String.valueOf(System.nanoTime());
			System.out.println(nano);
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
}
