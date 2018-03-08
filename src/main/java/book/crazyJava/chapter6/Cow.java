package book.crazyJava.chapter6;

/**
 * �ڲ����ʹ��
 * @author st-yz2011
 *
 */
public class Cow {
	private double weight;
	
	public Cow(){}
	public Cow(double weight) {
		this.weight = weight;
	}
	
	//����һ���Ǿ�̬�ڲ���
	private class CowLeg {
		private double lenght;
		private String color;
		
		public CowLeg() {
		}
		public CowLeg(double lenght, String color) {
			this.lenght = lenght;
			this.color = color;
		}
		
		public void info() {
			System.out.println("inner-color:" + color + ", inner-len:" + lenght);
			System.out.println("inner-outer-weight:" + weight);
		}
	}//end innerClass
	
	public void test() {
		CowLeg cl = new CowLeg(1.73, "����");
		cl.info();
	}
	
	public static void main(String[] args) {
		Cow cow = new Cow(200.00);
		cow.test();
	}
}
