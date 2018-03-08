package util.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreatSqlUitl {
	BufferedReader br; 
	BufferedWriter bw;
	
	public CreatSqlUitl() {
		try {
			br = new BufferedReader(
					new FileReader(new File("D://logs//char.txt")));
			bw = new BufferedWriter(
					new FileWriter(new File("D://logs//charSQL.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CreatSqlUitl f00 = new CreatSqlUitl();
		f00.isNotEmpty();
		System.out.println("expect you");
	}
	
	/**
	 * 根据字符串生成sql代码
	 */
	public void insertSQL() {
		try {
			bw.write("INSERT INTO VATCCS (");
			bw.write("\r\n");//换行符
			String line = "";
			int count = 0;
			StringBuffer sb = new StringBuffer();
			while((line = br.readLine()) != null) {
				count++;
				bw.write(line + ",");
				sb.append("#" + new String(line).toLowerCase()+ ":VARCHAR#,");
				bw.write(" ");	
				sb.append(" ");
				if(count%5 == 0) {
					bw.write("\r\n\t");//换行符
					sb.append("\r\n\t");
				}
			}
			bw.write("\r\n) VALUES (\r\n");
			bw.write(sb.toString());
			bw.write("\r\n)");
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void isNotEmpty() {
		try {
			String line = null;
			while((line = br.readLine()) != null) {
				line = line.trim();
				bw.write("<isNotEmpty prepend=\",\" property=\"" + line + "\">\r\n");
				bw.write("\t" + line + "=#" + line + "#\r\n");
				bw.write("</isNotEmpty>\r\n");
			}
			bw.flush();
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


