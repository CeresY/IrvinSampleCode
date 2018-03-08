package corejava.math;

import java.util.Random;

import org.junit.Test;

public class MathT {
	@Test
	public void randomDouble() {
		int counter = 10;
		for(int i=0; i<counter; i++) {
			double d = Math.random();
			System.out.println("d=" + d + "\td*99=" + d*99);
		}
	}
	
	@Test
	public void test() {
		int i = 5;
		System.err.println("整除:" + i/4);
	}
	
	@Test
	public void random() {
		int i = new Random().nextInt(100);
		System.out.println(i);
	}

	@Test
	public void only() {
		// 理论上存在重复的可能，可以在后面再加上一个随机字符串
	    for (int i = 0; i < 10; i++) {
	        long time = System.nanoTime();
	        long mills = System.currentTimeMillis();
	        System.out.println(time + "\t" + mills);
	    }
	}
}
