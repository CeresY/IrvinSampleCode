package book.thinkInJava.test;

public class ChildT extends FatherT {

	public ChildT() {
		System.out.println("ChildT construct");
		//f1();
	}
	
	public static void main(String[] args) {
		FatherT c1 = new ChildT();
		c1.f1();
	}
	
}
