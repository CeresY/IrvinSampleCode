package corejava.character;

public class CharT {

	public static void main(String[] args) {
		CharT foo = new CharT();
		foo.chooseAlphabet();
	}

	/**
	 * �ַ���ֻ��������
	 */
	public void chooseAlphabet() {
		String s1 = "abc56fa55f3a6df6a";
		byte[] bt = s1.getBytes();
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<bt.length; i++) {
			char c = (char)bt[i];
			if(c >= 48 && c <= 57) {
				sb.append(c);
			}
		}
		System.out.println(sb.toString());
	} 
	
}
