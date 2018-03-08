package corejava.control;

public class ExcuteFlow {
	
	private String property = test1("p1");
	
	{
		test1("xp");
		test2("xpxpxxpxpx");
	}
	
	public ExcuteFlow() {
		test1("constructor");
	}
	
	static {
		test1("static catch");
	}
	
	private String property2 = test1("p2");

	public static String test1(String str) {
		System.out.println("test1-" + str);
		return str+" complete";
	}
	
	public String test2(String str) {
		System.out.println("test1-" + str);
		return str+" complete";
	}
	
	public static void main(String[] args) {
		ExcuteFlow f = new ExcuteFlow();
	}
}
