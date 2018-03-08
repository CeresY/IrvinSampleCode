package corejava.string;

public class FinalString {
	public static String appendStr(String s) {
		s += "bbb";
		return s;
	}
	
	public static void main(String[] args) {
		String s = new String("abcaef");
		String ns = appendStr(s);
		System.out.println(ns);
		System.currentTimeMillis();
	}
}
