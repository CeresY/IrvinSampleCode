package book.crazyJava.chapter6;

public class DiscernVariable {
	private String field = "�ⲿ��ĳ�ԱFIELD.";
	private String outName = "out-name����һ���ֶ�";
	
	private class InnerClass {
		private String field = "�ڲ���ĳ�ԱFIELD";
		private void info() {
			String field = "�ڲ���ľֲ�FIELD";
			System.out.println("Outer = " + DiscernVariable.this.field);
			System.out.println("Inner.field = " + this.field);
			System.out.println("Inner.part = " + field);
			System.out.println("Outer-outfield = " + outName);
		}
	}
	
	
	public void outerMethod() {
		//System.out.println("�ⲿ����" + new InnerClass().info());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DiscernVariable outer = new DiscernVariable();
		outer.new InnerClass().info();
		System.out.println(outer.new InnerClass().field);
	}

}
