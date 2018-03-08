package util.str;

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
}
