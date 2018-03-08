package corejava.keyWord;

import corejava.model.Student;

public class FinalTest {
	private final int i = 1;
	private final String str = "ok";
	private final Student s = new Student("name_1");
	public static void main(String[] args) {
		FinalTest foo = new FinalTest();
		foo.test3();
//		foo.test2(2);
//		System.out.println(i);
//		FinalClass fc = new FinalClass("alan", 20, new AbleClass("p1", "p2"));
//		fc = new FinalClass("alan", 20, new AbleClass("p1", "p2"));
	}
	
	public void test3() {
		Integer i1 = new Integer(3);
		Integer i2 = Integer.valueOf(3);
		Integer i3 = Integer.valueOf(3);
		System.out.println(i1 == i2);
		System.out.println(i2 == i3);
	}
	
	public void test2(final int a) {
		System.out.println(a);
	}
	
	public void test1() {
		//str = "buok";
		//s = new Student("name_2");
		s.name="name_replae";
		System.out.println(s);
	}
}
