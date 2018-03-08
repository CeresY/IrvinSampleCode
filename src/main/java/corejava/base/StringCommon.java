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
		System.out.println(st1==st2);
		System.out.println(st1.endsWith(st2));
		System.out.println(st1.hashCode());
		System.out.println(st2.subSequence(0, 3));
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

}
