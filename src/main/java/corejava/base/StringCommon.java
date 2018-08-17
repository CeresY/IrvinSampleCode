package corejava.base;

import org.junit.Test;

public class StringCommon {
	@Test
	public void logic() {
		System.out.println(false&&false);
		System.out.println(false&false);
		System.out.println(true&&true);
		System.out.println(true&true);
		System.out.println(true&&false);
		System.out.println(true&false);
		System.out.println(false&&true);
		System.out.println(false&true);
	}
	
	@Test
	public void equalsM() {
		String st1 = "hello";
		String st2 = "hello";
		String st3 = "XDFs";
		System.out.println(st1==st2);
		System.out.println(st1.endsWith(st2));
		System.out.println(st1.hashCode());
		System.out.println(st2.subSequence(0, 3));
		System.out.println(st3.toLowerCase());
	}
	
	private String filterCustomerNo(String customerNo)
	{
		if (customerNo == null)
			return "";
		String realCustomerNo = customerNo;
		while (realCustomerNo.length()>0 && "0".equals(realCustomerNo.substring(0, 1)))
		{
			realCustomerNo = realCustomerNo.substring(1);
		}
		return realCustomerNo;
	}
	
	private String filterCustomerNo2(String customerNo)
	{
		if (customerNo == null)
			return "";
		while (customerNo.length()>0 && "0".equals(customerNo.substring(0, 1)))
		{
			customerNo = customerNo.substring(1);
		}
		return customerNo;
	}

	@Test
	public void testValueOf() {
		System.out.println(String.valueOf(null));
	}
	
	@Test
	public void testLen() {
		String[] strs = {"9d63ebd2ac58c5f50c7b62b24358411be72bf560", "f541c9f6621fc277e07924d41e27c0693a3ed8c1", "bb2b94da2fb09c2b591be56ebfc30dd957d0a008",
				"0b3bb327a7b481dd5d12cd952b757595e01a70c4", "8279d9ae7bb72ffdba3a6929ed77f3ccd5cce6b1", "64221d4b614e5911eb812f3ccdf3d342eb5d9f9a",
				"955b9fd4e32ec51fd8c48c67bd50a6b071ae2cdd","8ec652a101aa2381f5b82a08da4bc9c772b9b166"};
		for(String str : strs) {
			System.out.println(str+"="+str.length());
		}
	}
}
