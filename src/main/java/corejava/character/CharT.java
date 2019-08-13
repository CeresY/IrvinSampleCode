package corejava.character;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public class CharT {

	@Test
	public void chooseAlphabet() {
		String s1 = "abc56fa55f3a6df6a";
		byte[] bt = s1.getBytes();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<bt.length; i++) {
			char c = (char)bt[i];
			if(c >= 48 && c <= 57) {
				sb.append(c);
			}
		}
		System.out.println(sb.toString());
	}

	@Test
	public void regex() {
		String content = "汉ab字12汉cd";
		try {
			int count = content.getBytes("UTF-8").length;
			System.out.println("长度="+count);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pattern = "[\u4e00-\u9fa5_a-zA-Z0-9_]{4,10}";
		boolean isMatch = Pattern.matches(pattern, content);
		System.out.println(isMatch);
	}
	
}
