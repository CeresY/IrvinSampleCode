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
	 * ����������ʽ���˹���
	 */
	public static void regex() {
		String csm_bennam = "{}[]().-���������ܣ�/ �����ۣݡ��������������ߡ�d12321231";
		String r_actnam = "{}[]().-���������ܣ�/ �����ۣݡ��������������ߡ�d12321231";
		String regex = "[\\��|\\��|\\��|\\��|\\��|\\��|\\��|\\��|\\��|\\��|\\��|\\��|\\��|\\{|\\}|\\[|\\]|\\(|\\)|\\.|\\-|\\��|\\��|\\��|\\��|\\��|\\��|\\/|\\ ]";
		String c = csm_bennam.replaceAll(regex, "");
		String r = r_actnam.replaceAll(regex, "");
		if(c.equals(r)) {
			System.out.println("���");
		} else {
			System.out.println("NO");
		}
	}
}
