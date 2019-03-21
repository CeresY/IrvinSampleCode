package jdk.feature;

public class DemoLazy {
	public static void main(String[] args) {
		//这里实例化对象，并没有执行 sayHi 中的方法  
		Person p1 = new Person() { 
			@Override  public void sayHi() {
				System.out.println("来至内部类的问候");  
			}  
		};  
		System.out.println("主函数运行");  
		//这里才输出  
		p1.sayHi();  
		//同理，这里是没有运行方法体的  
		Person p2 = ()-> System.out.println("来至 Lambda 的问候");  
		//调用的时候才运行  
		p2.sayHi();  
	} 
	interface Person {  
		void sayHi(); 
	}
}