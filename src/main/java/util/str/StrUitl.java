package util.str;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

public class StrUitl {
	
	public static void main(String[] args) {
		alph();
	}
	
	public static void alph() {
		int i = 1&0;
		int x;
		System.out.println(i);
		//System.out.println(x);
	}
	
	/**
	 * 测试正则表达式过滤功能
	 */
	public static void regex() {
		String csm_bennam = "{}[]().-《》＜＞＼／/ ｛｝［］【】（）．。－＿　d12321231";
		String r_actnam = "{}[]().-《》＜＞＼／/ ｛｝［］【】（）．。－＿　d12321231";
		String regex = "[\\｛|\\｝|\\［|\\］|\\【|\\】|\\（|\\）|\\．|\\。|\\－|\\＿|\\　|\\{|\\}|\\[|\\]|\\(|\\)|\\.|\\-|\\《|\\》|\\＜|\\＞|\\＼|\\／|\\/|\\ ]";
		String c = csm_bennam.replaceAll(regex, "");
		String r = r_actnam.replaceAll(regex, "");
		if(c.equals(r)) {
			System.out.println("相等");
		} else {
			System.out.println("NO");
		}
	}
	
	/**
	 * 截取混合字符串（如果出现汉字不会截取半个）
	 * @param str 字符串
	 * @param len 字节长度
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String splitChar(String str, int len) throws UnsupportedEncodingException {
		if(StringUtils.isBlank(str)) {
			return null;
		}
		
		// 如果字符串长度小于截取长度，直接返回字符串
		int total = str.getBytes("UTF-8").length;
		if(total <= len) {
			return str;
		}
		
		char[] ch = str.toCharArray();
		int currentLen = 0;
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<ch.length; i++) {
			currentLen += String.valueOf(ch[i]).getBytes("UTF-8").length;
			if(currentLen <= len) {
				sb.append(ch[i]);
			} else {
				break;
			}
		}
		return sb.toString();
	}
}
