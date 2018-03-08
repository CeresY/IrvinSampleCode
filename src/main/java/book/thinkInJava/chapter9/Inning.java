package book.thinkInJava.chapter9;

public abstract class Inning {
	Inning() throws Exception {
		
	}
	abstract void atBat();
	void walk() {
		System.out.println("walk... ...");
	}
}
