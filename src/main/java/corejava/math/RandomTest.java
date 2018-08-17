package corejava.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class RandomTest {
	@Test
	public void test1() {
		int[] specialChar = {33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,58,59,60,61,62,63,64,91,92,93,94,95,96,123,124,125,126};
		System.out.println("len="+specialChar.length);
		// 生成随机数最在max,最小min
		int max = 90, min = 65;
		for(int i=0; i<30; i++) {
			int r = new Random().nextInt(max-min+1)+min;
			System.out.println(String.valueOf((char)r));
		}
	}
	
	@Test
	public void time() {
		for(int i=0; i<30; i++) {
			System.out.println(Math.abs(new Random().nextInt()*10000));
			String nano = String.valueOf(System.nanoTime());
			System.out.println(nano);
		}
	}
	
	public String getRandom(int upper) {
		int i = new Random().nextInt(upper);
		if(i<65) {
			return getRandom(upper);
		} else {
			return String.valueOf((char)i);
		}
		
	}
	
	 // 将字母转换成数字_1  
	@Test
    public void t1() {
		String input = "sdfsd12sf";
        String reg = "[a-zA-Z]";  
        StringBuffer strBuf = new StringBuffer();  
        input = input.toLowerCase();  
        if (null != input && !"".equals(input)) {  
            for (char c : input.toCharArray()) {  
                if (String.valueOf(c).matches(reg)) {  
                    strBuf.append(c - 96);  
                } else {  
                    strBuf.append(c);  
                }  
            }  
            System.out.println(strBuf.toString());  
        } else {  
            System.out.println(input);  
        }  
    }  
  
    // 将字母转换成数字
	@Test
    public void letterToNum() {
		String input = "ABC";
        for (byte b : input.getBytes()) {  
            System.out.println(b + "|" + (b - 96));
        }  
    }  
  
    // 将数字转换成字母  
    public static void numToLetter(String input) {  
        for (byte b : input.getBytes()) {  
            System.out.print((char) (b + 48));  
        }  
    }
    
    @Test
    public void randomArray() {
    	try {
			Integer[] array = this.getRandom(20, 7, 8, false);
			for(int i=0; i<array.length; i++) {
				System.out.print(array[i] + "\t");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
	 * 获取随机整数
	 * @return
	 */
	private Integer[] getRandom(int max, int min, int len, boolean repeat) throws Exception{
		if(!repeat) { // 不可重复
			if(max-min+1<len) {
				throw new Exception("随机数范围小于"+len+"位");
			}
		}
		
		Random r = new Random();
		Integer[] result = new Integer[len];
		// 可重复
		if(repeat) {
			for(int i=0; i<len; i++) {
				result[i] = r.nextInt(max-min+1)+min;
			}
		} 
		// 不可重复
		else {
			Set<Integer> rs = new HashSet<Integer>();
			while(true) {
				rs.add(r.nextInt(max-min+1)+min);
				if(rs.size() == len) {
					break;
				}
			}
			result = rs.toArray(result);
		}
		return result;
	}
	
	@Test
	public void testGetPassword() {
		for(int i=0; i<5; i++) {
			try {
				this.getPassword();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void getPassword() throws Exception {
		boolean isUpper = true, isLower = false, isNumber = true, isSpecial = false;
		
		Random r = new Random();
		int[] specialChar = {33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,58,59,60,61,62,63,64,91,92,93,94,95,96,123,124,125,126};
		int maxUpper = 90, minUpper = 65, maxLower = 122, minLower = 97, maxNumber = 57, minNumber = 48;
		
		// 密码组合
		
		int count = 0;
		List<String> ofPatry = new ArrayList<>();
		// 大写字母
		if(isUpper) {
			++count;
			String p2 = String.valueOf((char)(r.nextInt(maxUpper-minUpper+1)+minUpper));
			ofPatry.add(p2);
		}
		// 小写字母
		if(isLower) {
			++count;
			String p1 = String.valueOf((char)(r.nextInt(maxLower-minLower+1)+minLower));
			ofPatry.add(p1);
		}
		// 数字
		if(isNumber) {
			++count;
			String p3 = String.valueOf((char)(r.nextInt(maxNumber-minNumber+1)+minNumber));
			ofPatry.add(p3);
		}
		// 特殊字符
		if(isSpecial) {
			++count;
			int sp1 = specialChar[r.nextInt(specialChar.length)];
			String p4 = String.valueOf((char)sp1);
			ofPatry.add(p4);
		}
		
		// 密码来源取ascii范围
		int maxAscii = 126, minAscii = 33;
		
		// 密码长度
		int maxLen = 20, minLen = 8; // 密码长度范围
		int passwordLen = r.nextInt(maxLen-minLen+1)+minLen;
		
		String[] password = new String[passwordLen];
		List<Integer> position = new ArrayList<>(); // 密码位置
		Integer[] positionArray = this.getRandom(passwordLen-1, 0, count, false);
		
		
		
		for(int i=0; i<positionArray.length; i++) {
			position.add(positionArray[i]);
			password[positionArray[i]] = ofPatry.get(i);
		}
		
		for(int i=0; i<passwordLen; i++) {
			if(!position.contains(i)) {
				while(true) {
					int other = r.nextInt(4)+1;
					if(other == 1 && isUpper) { // 大写
						password[i] = String.valueOf((char)(r.nextInt(maxUpper-minUpper+1)+minUpper));
						break;
					}
					else if(other == 2 && isLower) { // 小写
						password[i] = String.valueOf((char)(r.nextInt(maxLower-minLower+1)+minLower));
						break;
					}
					else if(other == 3 && isNumber) { // 数字
						password[i] = String.valueOf((char)(r.nextInt(maxNumber-minNumber+1)+minNumber));
						break;
					}
					else if(other == 4 && isSpecial) { // 特殊字符
						int sp1 = specialChar[r.nextInt(specialChar.length)];
						password[i] = String.valueOf((char)sp1);
						break;
					}
				}
			}
		}
		System.out.print("len=" + passwordLen + "\t");
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<password.length; i++) {
			sb.append(password[i]);
		}
		System.out.println(sb.toString());
	}
	
	@Test
	public void testCheck() {
		System.out.println(this.checkPassword("lsdf234#%"));
		System.out.println(this.checkPassword("21341#%12"));
		System.out.println(this.checkPassword("erty56u3456"));

	}
	
	/**
	 * 校验密码的复杂度
	 * @param password
	 * @return
	 */
	private boolean checkPassword(String password) {
		boolean isUpper = false, isLower = false, isNumber = true, isSpecial = true;
		int[] specialChar = {33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,58,59,60,61,62,63,64,91,92,93,94,95,96,123,124,125,126};
		int maxUpper = 90, minUpper = 65, maxLower = 122, minLower = 97, maxNumber = 57, minNumber = 48;
		
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
		if(mark) { // 存在
			flag = isUpper;
		} else { // 不存在
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
		if(mark) { // 存在
			flag = isLower;
		} else { // 不存在
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
		if(mark) { // 存在
			flag = isNumber;
		} else { // 不存在
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
		if(mark) { // 存在
			flag = isSpecial;
		} else { // 不存在
			flag = isSpecial ? false : true;
		}
		
		return flag;
	}
}
