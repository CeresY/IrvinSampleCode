package util.db;
/**
 * 生成POJO类和SQLMAP的工具类
 * 操作步骤：
 * 1.从数据库中拷贝名称、类型、注释列，放到指定路径的txt文件中
 * 2.修改此类的读取文件路径，执行main方法
 * 3.结果在控制台输出，拷贝到sqlMap和POJO类中
 * 20140617 zhangchao
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CreateResultMapAndPojo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String fileName = "D:\\code\\resultmap.txt";
		createResultMap(fileName);
		createPojo(fileName);
	}

	
	public static void createResultMap(String fileName)throws IOException{
		System.out.println("**********生成sqlMap开始**********");
		//固定格式
		String s1 = "<result property='";
		String s2 = "' column='";
		String s3 = "' /> <!-- ";
		String s4 = " -->";
		String s5 = "";
		File file = new File(fileName);
		BufferedReader reader = null;
		String s = null;
		int j = 0;//行号，方便定位文件哪一行有误
		try {
			FileReader fis = new FileReader(file);
			reader = new BufferedReader(fis);
			//读取文件
			while ((s = reader.readLine()) != null) {
				j++;
				String[] dates = s.split("	");
				//判断文件格式
				if(dates.length!=3){
					throw new RuntimeException("第"+j+"文件内容格式有误！");
				}
				String date1 = dates[0];//参数名
//				String date2 = dates[1];
				String date3 = dates[2];//注释
				StringBuffer sb = new StringBuffer();
				//数据库中参数名为大写，需转换，并去掉下划线，下划线后首位大写
				for(int i=0;i<date1.length();i++){
					char c1 = date1.charAt(i);
					String s6 = String.valueOf(c1).toLowerCase();
					if("_".equals(s6)){
						char s7 = date1.charAt(i+1);
						String s8 = String.valueOf(s7).toUpperCase();
						i++;
						sb.append(s8);
					}else{
						sb.append(s6);
					}
				}
				s5 = s1 + sb + s2 + date1 + s3 + date3 + s4 ;
				s5 = s5.replaceAll("'", "\"");
				System.out.println(s5);
	        }
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}finally {
			reader.close();
		}
		System.out.println("**********生成sqlMap结束**********");
		System.out.println("");
		System.out.println("");
	}
	
	public static void createPojo(String fileName)throws IOException{
		System.out.println("**********生成POJO类开始**********");
		File file = new File(fileName);
		BufferedReader reader = null;
		String s = null;
		int j = 0;//行号，方便定位文件哪一行有误
		try {
			FileReader fis = new FileReader(file);
			reader = new BufferedReader(fis);
			//读取文件
			while ((s = reader.readLine()) != null) {
				j++;
				String[] dates = s.split("	");
				//判断文件格式
				if(dates.length!=3){
					throw new RuntimeException("第"+j+"文件内容格式有误！");
				}
				String date1 = dates[0];//参数名
				String date2 = dates[1];//类型
				String date3 = dates[2];//注释
				StringBuffer sb = new StringBuffer();
				String s1 = "";
				//数据库中参数名为大写，需转换，并去掉下划线，下划线后首位大写
				for(int i=0;i<date1.length();i++){
					char c1 = date1.charAt(i);
					String s6 = String.valueOf(c1).toLowerCase();
					if("_".equals(s6)){
						char s7 = date1.charAt(i+1);
						String s8 = String.valueOf(s7).toUpperCase();
						i++;
						sb.append(s8);
					}else{
						sb.append(s6);
					}
				}
				//类型转换
				if("INTEGER".equals(date2)){
					//如果是批次号或流水号则为Long类型
					if("BAT_SEQ".equals(date1)||"TRANS_ID".equals(date1)){
						s1 = "Long ";
					}else{
						s1 = "Integer ";
					}
				}else if("DATE".equals(date2)){
					s1 = "Date ";
				}else if(date2.startsWith("NUMBER")){
					s1 = "BigDecimal ";
				}else if(date2.startsWith("VARCHAR")){
					s1 = "String ";
				}else if(date2.startsWith("TIMESTAMP")){
					s1 = "TimeStamp ";
				}else{
					throw new RuntimeException("第"+j+"行参数类型有误");
				}
				String s2 = "private " + s1 + sb + "; //" + date3;
				System.out.println(s2);
	        }
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}finally {
			reader.close();
		}
		System.out.println("**********生成POJO类结束**********");
		System.out.println("");
	}
}
