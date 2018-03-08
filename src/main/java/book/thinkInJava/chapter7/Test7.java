package book.thinkInJava.chapter7;

public class Test7 {
	{
		System.out.println("Test7.common area before");
	}
	
	Egg2 e = new Egg2();
	int i=1;
	
	static { 
		System.out.println("Test7.static area");
	}
	
	{
		System.out.println("Test7.common area after");
	}
	
	public Test7(int param) {
		System.out.println("Test7 construct");
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test7(3);
	}

}
