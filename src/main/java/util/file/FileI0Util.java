package util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileI0Util {
	static String fileFullName;

	private static String Hex[] = { "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "A", "B", "C", "D", "E", "F" };

	private static int[] ic07Loop = { 8, 8, 8, 12, 27, 32, 15, 1, 4, 3, 21, 22,
			4, 30, 14, 200, 140, 160, 225, 8, 22, 22, 22, 22, 32, 32, 12, 70,
			35, 100, 12, 70, 35, 100, 12, 70, 35, 100, 1, 70, 70, 70 };

	private static int[] ic06Loop = { 8, 8, 8, 12, 27, 32, 15, 1, 4, 3, 21, 22,
			4, 30, 14, 200, 140, 160, 225, 8, 22, 22, 22, 22, 32, 32, 12, 70,
			35, 100, 12, 70, 35, 100, 12, 70, 35, 100, 70, 70, 70 };

	public static void main(String[] args) {
		/*
		 * String s = "12300.00"; StringBuffer sb = new StringBuffer(); for(int
		 * i=0; i<s.length(); i++) { char c = s.charAt(i); if(c == 46) { break;
		 * } sb.append(c); } System.out.println("c: "+sb.toString());
		 */

		/*toStr();*/

		
		 formatOne(); 
		 try { 
			 Process process = Runtime.getRuntime()
			 	.exec("C://Soft//Notepad++//notepad++.exe "+fileFullName); 
		 } catch (IOException e) {
			 e.printStackTrace(); 
		}
		 

		//hexToTxt();
		
		System.out.println("... ...done");
	}

	// 把一个文本文件按照定长格式打印出来
	public static void formatOne() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"D://logs//in.txt")));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			str(sb.toString(), ic07Loop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * //把16进制文本转换成字符文本
	 */
	public static void hexToTxt() {
		try {
			FileInputStream fis = new FileInputStream(new File("D://logs//A_16.txt")); 
			FileOutputStream fos = new FileOutputStream(new File("D://logs//A_txt.txt")); 
			byte b[] = new byte[512]; 
			int c = 0; 
			while((c = fis.read(b)) != -1) {
				byte[] bs = HexStrToBinary(new String(b, 0, c)); 
				fos.write(bs);
				fos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void toStr() {
		try {
			/*
			 * //把16进制文本转换成字符文本 FileInputStream fis = new FileInputStream(new
			 * File("D://logs//ic07Respon.txt")); FileOutputStream fos = new
			 * FileOutputStream(new File("D://logs//ic07ResponStr.txt")); byte
			 * b[] = new byte[512]; int c = 0; while((c = fis.read(b)) != -1) {
			 * byte[] bs = HexStrToBinary(new String(b, 0, c)); fos.write(bs); }
			 */

			// 把字符串文本（含有换行符）转换成不含有换行符的文本
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"D://logs//src16.txt")));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"D://logs//src16_format.txt")));
			String line = "";
			while ((line = br.readLine()) != null) {
				bw.write(line);
				bw.flush();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 16进制字符串,转换为字节数组
	 */
	public static byte[] HexStrToBinary(String str) {
		int len = str.length() / 2;
		byte[] bs = new byte[len];
		for (int i = 0; i < len; i++) {
			int r1 = rank(String.valueOf(str.charAt(i * 2)));
			byte high = (byte) (r1 << 4);
			int r2 = rank(String.valueOf(str.charAt(i * 2 + 1)));
			byte low = (byte) r2;
			bs[i] = (byte)(high | low);
		}
		return bs;
	}

	public static int rank(String str) {
		for (int i = 0; i < Hex.length; i++) {
			if (str.equals(Hex[i])) {
				return i;
			}
		}
		return 0;
	}

	public static void str(String str, int[] lens) {
		BufferedWriter out = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'_'HHmmss");
			String dateStr = sdf.format(new Date());
			File des = new File("D://logs//out_" + dateStr + ".txt");
			fileFullName = "D://logs//out_" + dateStr + ".txt";
			if (!des.exists()) {
				des.createNewFile();
			}
			out = new BufferedWriter(new FileWriter(des));

			byte[] bs = str.getBytes();
			int skip = 0;
			
			int lensTot = 0;
			for(int t=0; t<lens.length; t++) {
				lensTot += lens[t];
			}
			
			int N = bs.length/lensTot;//N个循环
			System.out.println(N + "个循环=" + bs.length + "/" + lensTot);
			
			for(int n=0; n<N; n++) {
				out.write("------------------loop" + (n+1) + "------------------\r\n" );
				for (int i = 0; i < lens.length; i++) {
					int len = lens[i];
					String s = new String(bs, skip, len);
					skip += len;
					String property = i + "(" + len + ")[" + s + "]";
					// System.out.println(property);
					out.write(property);
					out.write("\r\n");// 换行符
				}
				out.write("size=" + skip);
				out.flush();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String formatStr(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == 46) {
				break;
			}
			sb.append(c);
		}
		return sb.toString();
	}

}
