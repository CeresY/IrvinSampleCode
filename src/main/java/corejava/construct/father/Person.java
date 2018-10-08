package corejava.construct.father;

public class Person {
	public int age = 31;
	private int male = 1;

	{
		System.out.println("成员代码块");
	}

	static {
		System.out.println("静态代码块");
	}

	public Person(int age) {
		this.age = age;
		System.out.println("构造");
	}

	public static void main(String[] args) {
		Person p = new Person(3);
	}
}
