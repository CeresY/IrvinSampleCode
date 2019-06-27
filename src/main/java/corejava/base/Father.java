package corejava.base;

import org.junit.Test;

public class Father {
	private String name;
	
	public String info;

	public Father() {
	}

	public Father(String name) {
		this.name = name;
		this.info = name;
		System.out.println("Father construct");
	}

	public void say() {
		System.out.println("Father say: " + this.name);
	}

	@Test
	public void test1() {
		boolean flag = true;
		String message = "正常";
		System.out.println("(1)message = " + message+", flag="+flag);
		this.test1(flag, message);
		System.out.println("(2)message = " + message+", flag="+flag);
	}

	public void test1(boolean flag, String message) {
		flag = false;
		message = "异常";
		System.out.println("(inner-1)message = " + message+", flag="+flag);
	}

	public static void main(String[] args) {
		Father foo = new Father();
		foo.test1();
	}
}
