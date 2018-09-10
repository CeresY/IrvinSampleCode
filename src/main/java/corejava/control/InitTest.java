package corejava.control;
/**
  * @author yangzhan
  * @date 2018年8月23日
  */
public class InitTest {
	private static int global = 0;
	
	public static int getGlobal() {
		return global;
	}
	
	public static void setGlobal(int number) {
		InitTest.global = number;
	}

	public InitTest() {
		InitTest.setGlobal(5);
	}
	
	public static void main(String[] args) {
		System.out.println(InitTest.getGlobal());
	}
}
