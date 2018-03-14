package book.headFirst.adapter;
/**
 * <b>���������ģʽ: </b><br>
 * ���������ģʽ����˼����ǣ���һ��Source�࣬ӵ��һ��������
 * �����䣬Ŀ��ӿ���Targetable��ͨ��Adapter�࣬��Source�Ĺ�����չ��Targetable�
 * @author yangzhan
 *
 */
public class AdapterMain extends Source implements Targetable {

	@Override
	public void m2() {
		System.out.println("������m2"); 
	}

	public static void main(String[] args) {
		Targetable target = new AdapterMain();
		target.m2();
		target.m1();
	}
}
