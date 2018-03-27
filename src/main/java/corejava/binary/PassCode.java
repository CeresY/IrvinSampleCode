package corejava.binary;

import org.junit.Test;

/**
 * 汉字均以2个字节
 * @author st-yz2011
 *
 */
public class PassCode {
	private static String Hex[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
	private static String b2[] = {
		"0000","0001","0010","0011",
		"0100","0101","0110","0111",
		"1000","1001","1010","1011",
		"1100","1101","1110","1111",
	};
	
	private static byte b[] = {127,-128};
	
	@Test
	public void hex2rgb() {
		String hex = "#1791C8".replace("#", "");
		String r = hex.substring(0, 2);
		String g = hex.substring(2, 4);
		String b = hex.substring(4, 6);
		System.out.println(Integer.parseInt(r, 16));
		System.out.println(Integer.parseInt(g, 16));
		System.out.println(Integer.parseInt(b, 16));
	}
	
	/**
	 * 字节数组转换成16进制
	 * @param b
	 */
	public static void byteToHex(byte[] b) {
		int pos = 0;
		String s = "";
		for(int i=0; i<b.length; i++) {
			pos = (b[i]&0xF0)>>4;//高位
			s += Hex[pos];
			pos = (b[i]&0x0F);//低位
			s += Hex[pos]+" ";
		}
		System.out.println("16进制\n"+s);
	}
	
	/**
	 * 字节数组转换成2进制
	 * @param bs
	 */
	public static void toBaniry2(byte[] bs) {
		int pos = 0;
		System.out.print("2进制：\n");
		for(byte b : bs) {
			//高4
			pos = (b&0xF0)>>4;
			String high = b2[pos];
			//低4
			pos = (b&0x0f);
			String low = b2[pos];
			System.out.print(high+low+" ");
		}
		System.out.print("\n");

	}
	
}
