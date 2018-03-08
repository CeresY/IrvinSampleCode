package corejava.string;

import org.junit.Test;

public class Replace {
	
	@Test
	public void regex() {
		String v = "abc{0}efg{1}123";
		System.out.println(v.replace("\\{([^}]+)\\", "F20"));
	}
	
	@Test
	public void split() {
		String str = "项目经理-国际项目";
		System.out.println(str.replace("项目经理-", ""));
		System.out.println(str.startsWith("项目经理-"));
	}
}
