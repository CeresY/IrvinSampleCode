package corejava.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegularExpressionTest {
	@Test
	public void pattern() {
		String str = "ab12eft89abiiab34iwo我sbabfjb gjs3abu98kw3ab90sfj";
		// 1
		String regex = "ab(?:\\d+)";
//		find(str, regex);
		// 2
		String regex2 = "(?<=\\d+)ab";
//		find(str, regex2);
		// 3
		String str3 = "aaaadb aafd aafsaa aaaaaf aaa    b";
		String regex3 = "a{2,4}(?=\\s)";
//		find(str3, regex3);
		// 4
		String str4 = "asxxaxaaxxdb aaxxxxfd aafsaa aaxxxxxxaaaf aaa  x  b";
		String regex4 = "a(?:x{1,})";
//		find(str4, regex4);
		// 5 
		String str5 = "12312_X_4567812x_fsafasx_Xfsdf";
		String regex5 = "\\d{1,}";
		find(str5, regex5);
		// 替换
		System.out.println(str5.replaceAll("(X|x)", "XC"));
		
	}
	
	@Test
	public void chinese() {
		String str = "abc_汉字fjfk5中6文7ad汉族678ol,2017-12-20 21:50:59.jhgfcn6m汉字567iolansdlfasd=-09";
		String regex = "50";
		find(str, regex);
		matcher(str, regex);
	}
	
	@Test
	public void test1() {
		matcher("", "^[a-zA-Z]\\w{7,31}$");
		matcher("${User?id}", "^\\$\\{(User)(_){0,1}.*(id)\\}$");
	}
	
	/**
	 * 验证是否匹配
	 * @param str 校验字符串
	 * @param regex 表达式
	 * @return
	 */
	public static void matcher(String str, String regex) {
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(str);
		System.out.println(m.matches());
	}
	
	public static void find(String str, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			System.out.println(m.group() + "\t" + m.start() + "\t" + m.end());
		}
		System.out.println("-----------------");
	}
	
}
