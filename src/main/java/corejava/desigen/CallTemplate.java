package corejava.desigen;

public class CallTemplate {
	public void execute(CallBack back) {
		back.roll();
		String result = back.display("如果有返回值 ");
		System.out.println(result);
		System.out.println("method...");
	}
}
