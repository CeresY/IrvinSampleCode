package util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DelFile {
	private static final File in = new File("D:/logs/VAPSWAR.war"); 
	private static String rubbishPath = "D://Users//st-yz2011//Desktop//temp//excel//rubbish//";
	
	public static void main(String[] args) {
		creatFile();
	}
	
	/**
	 * 目录不存在创建目录
	 */
	public static void creatFile() {
		File rubbish = new File(rubbishPath);
		if(rubbish.exists()) {
			System.out.println("目录存在");
		}else{
			System.out.println("目录不在");
			rubbish.mkdir();
		}
	}
	
	/**
	 * 删除所有子目录下的文件，只保留文件夹
	 * @param src
	 */
	public static void delfileCall(File src) {
		if(src.exists()) {
			File[]  fils = src.listFiles();
			for(File f : fils) {
				if(f.isDirectory()) {
					delfileCall(f);
				} else{
					System.out.println(f.getName());
				}
			}
		}
	}
	
	/**
	 * 复制“指定文件”到“目标目录”下
	 */
	public static void copyFile() {
		String src = "";
		//index[][0]:源文件，index[][1]：目标文件
		String[][] f = {
			{"D:/Users/st-yz2011/Desktop/temp/20151113-AM.png", "D:/logs/com/20151113-AM.png"},
			{"D:/Users/st-yz2011/Desktop/temp/20151113-PM.png", "D:/logs/com/1/20151113-PM.png"}
		};
		for(int i=0; i<f.length; i++) {
			try {
				FileInputStream in = new FileInputStream(new File(f[i][0]));
				FileOutputStream out = new FileOutputStream(new File(f[i][1]));
				byte[] b = new byte[1024 * 10];
				int end = 0;
				while((end = in.read(b)) != -1) {
					out.write(b, 0, end);
				}
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
