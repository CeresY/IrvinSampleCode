package corejava.reflect.proxy;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FootballPlayer implements Player {

	@Override
	public void work() {
		System.out.println("我是一名足球运动员");
	}

	@Override
	public void longtime() {
		System.out.println("工作了10年");
	}
	
	public void superclass() {
		Class clazz = getClass();
		System.out.println(clazz.getName());
		Type t = clazz.getGenericSuperclass();
		System.out.println(t);
		
		ArrayList<String> list = new ArrayList<String>();
		Type t2 = list.getClass().getGenericSuperclass();
		System.out.println(t2);
	}

	public static void main(String[] args) {
		FootballPlayer foo = new FootballPlayer();
		foo.superclass();
	}
}
