package book.crazyJava.chapter6;

public class DiscernVariable {
	private String field = "外部类的成员FIELD.";
	private String outName = "out-name这是一个字段";
	
	private class InnerClass {
		private String field = "内部类的成员FIELD";
		private void info() {
			String field = "内部类的局部FIELD";
			System.out.println("Outer = " + DiscernVariable.this.field);
			System.out.println("Inner.field = " + this.field);
			System.out.println("Inner.part = " + field);
			System.out.println("Outer-outfield = " + outName);
		}
	}
	
	
	public void outerMethod() {
		//System.out.println("外部方法" + new InnerClass().info());
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
