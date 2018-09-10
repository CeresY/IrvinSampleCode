package corejava.string;

import org.junit.Test;

public class FinalString {
	/**特殊字符的ascii码分布范围*/
	public static int[] specialChar = {33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,58,59,60,61,62,63,64,91,92,93,94,95,96,123,124,125,126};
	
	/**大写字母、小写字母、数字 ascii码分布范围*/
	public static int maxUpper = 90, minUpper = 65, maxLower = 122, minLower = 97, maxNumber = 57, minNumber = 48;
	
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
	
	@Test
	public void testValidate() {
		System.out.println(this.validatePassword("Techstar@2018*"));
	}
	
	
	/**
	 * 校验密码的复杂度
	 * @param password
	 * @return
	 */
	public boolean validatePassword(String password) {
		boolean isUpper = true;
		boolean isLower = true;
		boolean isNumber = true;
		boolean isSpecial = false;
		
		boolean flag = true;
		byte[] bts = password.getBytes();
		boolean mark = false;
		
		// 大写字母
		for(byte b : bts) {
			if(b>=minUpper && b<=maxUpper) {
				mark = true;
				break;
			}
		}
		if(!mark){ // 不存在
			flag = isUpper ? false : true;
		}
		
		if(!flag) {
			return false;
		}
		
		// 小写字母
		mark = false;
		for(byte b : bts) {
			if(b>=minLower && b<=maxLower) {
				mark = true;
				break;
			}
		}
		if(!mark){ // 不存在
			flag = isLower ? false : true;
		}
		
		if(!flag) {
			return false;
		}
		
		// 数字
		mark = false;
		for(byte b : bts) {
			if(b>=minNumber && b<=maxNumber) {
				mark = true;
				break;
			}
		}
		if(!mark){ // 不存在
			flag = isNumber ? false : true;
		}
		
		if(!flag) {
			return false;
		}
		
		// 特殊字符
		mark = false;
		outLoop:for(int i=0; i<bts.length; i++) {
			int ascii = bts[i];
			for(int j=0; j<specialChar.length; j++) {
				if(ascii == specialChar[j]) {
					mark = true;
					break outLoop;
				}
			}
		}
		if(!mark){ // 不存在
			flag = isSpecial ? false : true;
		}
		
		return flag;
	}
}
