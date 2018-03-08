package util.db;
/**
 * ����POJO���SQLMAP�Ĺ�����
 * �������裺
 * 1.�����ݿ��п������ơ����͡�ע���У��ŵ�ָ��·����txt�ļ���
 * 2.�޸Ĵ���Ķ�ȡ�ļ�·����ִ��main����
 * 3.����ڿ���̨�����������sqlMap��POJO����
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
		System.out.println("**********����sqlMap��ʼ**********");
		//�̶���ʽ
		String s1 = "<result property='";
		String s2 = "' column='";
		String s3 = "' /> <!-- ";
		String s4 = " -->";
		String s5 = "";
		File file = new File(fileName);
		BufferedReader reader = null;
		String s = null;
		int j = 0;//�кţ����㶨λ�ļ���һ������
		try {
			FileReader fis = new FileReader(file);
			reader = new BufferedReader(fis);
			//��ȡ�ļ�
			while ((s = reader.readLine()) != null) {
				j++;
				String[] dates = s.split("	");
				//�ж��ļ���ʽ
				if(dates.length!=3){
					throw new RuntimeException("��"+j+"�ļ����ݸ�ʽ����");
				}
				String date1 = dates[0];//������
//				String date2 = dates[1];
				String date3 = dates[2];//ע��
				StringBuffer sb = new StringBuffer();
				//���ݿ��в�����Ϊ��д����ת������ȥ���»��ߣ��»��ߺ���λ��д
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
		System.out.println("**********����sqlMap����**********");
		System.out.println("");
		System.out.println("");
	}
	
	public static void createPojo(String fileName)throws IOException{
		System.out.println("**********����POJO�࿪ʼ**********");
		File file = new File(fileName);
		BufferedReader reader = null;
		String s = null;
		int j = 0;//�кţ����㶨λ�ļ���һ������
		try {
			FileReader fis = new FileReader(file);
			reader = new BufferedReader(fis);
			//��ȡ�ļ�
			while ((s = reader.readLine()) != null) {
				j++;
				String[] dates = s.split("	");
				//�ж��ļ���ʽ
				if(dates.length!=3){
					throw new RuntimeException("��"+j+"�ļ����ݸ�ʽ����");
				}
				String date1 = dates[0];//������
				String date2 = dates[1];//����
				String date3 = dates[2];//ע��
				StringBuffer sb = new StringBuffer();
				String s1 = "";
				//���ݿ��в�����Ϊ��д����ת������ȥ���»��ߣ��»��ߺ���λ��д
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
				//����ת��
				if("INTEGER".equals(date2)){
					//��������κŻ���ˮ����ΪLong����
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
					throw new RuntimeException("��"+j+"�в�����������");
				}
				String s2 = "private " + s1 + sb + "; //" + date3;
				System.out.println(s2);
	        }
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}finally {
			reader.close();
		}
		System.out.println("**********����POJO�����**********");
		System.out.println("");
	}
}
