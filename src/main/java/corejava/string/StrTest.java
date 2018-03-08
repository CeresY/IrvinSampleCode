package corejava.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StrTest {
	
	/**
	 * 截取汉字
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void splitChinese() throws UnsupportedEncodingException {
		int len = 7;
		String str = "a中b文c123汉字";
		char[] ch = str.toCharArray();
		int currentLen = 0;
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<ch.length; i++) {
			currentLen += String.valueOf(ch[i]).getBytes("UTF-8").length;
			if(currentLen <= len) {
				sb.append(ch[i]);
			}
		}
		System.out.println(sb.toString());
	}

	@Test
	public void aplist2() {
		String str = "a中b文c123汉字";
		char exp = 0;
		switch (exp) {
		case 0:
			int idx = 3;
			String splitStr = str.substring(0, idx);
			System.out.println("splitStr:" + splitStr);
			try {
				int move = idx;
				while(splitStr.getBytes("UTF-8").length > move) {
					System.out.println(splitStr.substring(0, --idx));
				}
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			break;
		case 1:
			try {
				byte[] b = str.getBytes("UTF-8");
				for(int i=0; i<b.length; i++) {
					System.out.println((char)b[i]);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			char[] ch = str.toCharArray();
			for(int i=0; i<ch.length; i++) {
				System.out.println(ch[i]);
			}
			break;
		default:
			break;
		}
		
	}
	
	@Test
	public void replaceTest() {
		StrTest obj = new StrTest();
		StrTest obj2 = null;
		if(obj2 instanceof StrTest) {
			System.out.println("正是在下".toLowerCase());
		} else {
			System.out.println("你认错人了".toLowerCase());
		}
	}
	
	@Test
	public void sub() {
		String s = "0123456";
		System.out.println(s.substring(0, 4));
	}
	
	@Test
	public void split() {
		String s = ",b,,c,";
		String[] arr = s.split(",");
		for(String str : arr) {
			System.out.println("["+str+"]");
		}
	}
	
	public void finalTest() {
//		String s1 = "aaa";
		String s1 = new String("aaa");
		//System.out.println(s1);
		
		//--------------
		int i=1;
		finalTest3(i);
		System.out.println(i);
	}
	
	@Test
	public void finalTest2() {
		System.out.println("abSDdF".toUpperCase());
		String str = "abc:assign";
		System.out.println(str.split(":")[0]);
	}
	
	public void finalTest3(int ii) {
		ii++;
	}
	
	public void init() {
		StringBuffer sb = new StringBuffer("A");
//		String str = "B";
		String str = new String("B");
		operate(sb, str);
		System.out.println("StringBuffer=" + sb);
		System.out.println("String=" + str);
	}
	
	public static void operate(StringBuffer sb, String str) {
		sb.append(str);
		System.out.println("operate-before = " + str);
		str += sb;
		System.out.println("str-after = " + str);
	}
	
}
