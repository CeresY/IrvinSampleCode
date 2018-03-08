package book.thinkInJava.test;

import java.util.Random;

public class Test {
	static Random r = new Random();
	
	public static void main(String[] args) {
		mathAbs();
	}
	
	public static void mathAbs() {
		for(int i=0; i<100; i++) {
			System.out.println(Math.abs(r.nextInt())%66);
		}
	}

}
