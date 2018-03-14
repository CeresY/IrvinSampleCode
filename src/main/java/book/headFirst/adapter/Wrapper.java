package book.headFirst.adapter;

/**
 * <b>�����������ģʽ: </b><br>
 * �����������ģʽ�Ļ���˼·�����������ģʽ��ͬ��ֻ�ǽ�Adapter�����޸ĳ�Wrapper��
 * ��β��̳�Source�࣬���ǳ���Source���ʵ�����Դﵽ��������Ե����⡣
 * @author yangzhan
 * @version 2016-3-25��V1<br>
 * 			2016-3-25��V2
 */
public class Wrapper implements Targetable {
	private Source source;
	public Wrapper(Source source) {
		this.source = source;
	}

	@Override
	public void m1() {
		System.out.println("����������-m1"); 
	}

	@Override
	public void m2() {
		System.out.println("����������-m2");
	}

	public static void main(String[] args) {
		Source source = new Source();
		Targetable target = new Wrapper(source);
		target.m1();
		target.m2();
	}
}
	