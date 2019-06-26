package jdk.feature;

import static java.lang.System.*;

public class DemoLazy {
	public static void main(String[] args) {
		//这里实例化对象，并没有执行 sayHi 中的方法  
		Person p1 = new Person() { 
			@Override  public void sayHi(String name) {
				out.println("来至内部类的问候 = ["+name+"]");
			}
		};
		out.println("主函数运行");
		//这里才输出  
		p1.sayHi("bobo");
		//同理，这里是没有运行方法体的
		Person p2 = (String name)-> out.println("来至 Lambda 的问候 = ["+name+"]");
		//调用的时候才运行  
		p2.sayHi("lucy");
	} 
	interface Person {  
		void sayHi(String name);
	}
}