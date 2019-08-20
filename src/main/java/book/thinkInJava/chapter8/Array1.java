package book.thinkInJava.chapter8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Array1 {
	static Random r = new Random();
	static String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
	"abcdefghijklmnopqrstuvwxyz";
	static char[] src = source.toCharArray();
	
	/**
	 * 生成length个字母的随机字符串。<br>
	 * <Strong>示例：</Strong><br>
	 * length=2;String = xy; length=4;String = xyxy;
	 * @param length 字符串个数
	 * @return
	 */
	public static String randString(int length) {
		char[] buf = new char[length];
		int rnd;
		for(int i=0; i<length; i++) {
			rnd = Math.abs(r.nextInt()) % src.length;
			buf[i] = src[rnd];
		}
		return new String(buf);
	}
	
	public static String[] randString(int length, int size) {
		String[] s = new String[size];
		for(int i=0; i<size; i++) {
			s[i] = randString(length);
		}
		return s;
	}
	
	public static void print(byte[] b) {
		int count = 0;
		for(int i=0; i<b.length; i++){
			System.out.print(b[i] + " ");
			if(++count%10 == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public static void print(String[] s) {
		int count = 0;
		for(int i=0; i<s.length; i++){
			System.out.print(s[i] + " ");
			if(++count%10 == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int searchLoc = 5;
		int loc;
		byte[] b = new byte[15];
		r.nextBytes(b);
		print(b);
//		Arrays.sort(b);
//		print(b);
		//搜索byte数组的指定位置
		//System.out.println("//------搜索byte[" + searchLoc + "]------");
		loc = Arrays.binarySearch(b, b[searchLoc]);
		System.out.println("Location of " + b[searchLoc] + " is " + loc);
		//
		AlphaComp ac = new AlphaComp();
		System.out.println("//------生成随机字符String[]------");
		String[] s = randString(4, 10);
		print(s);
		Arrays.sort(s, ac);
		print(s);
		loc = Arrays.binarySearch(s, s[searchLoc]);
		System.out.println("Location of " + s[searchLoc] + " is " + loc);
	}
}
