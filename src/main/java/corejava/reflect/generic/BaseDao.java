package corejava.reflect.generic;

import java.lang.reflect.Type;

public class BaseDao<T> {

	public BaseDao() {
		Class clazz = getClass();
		/*Type[] ts1 = clazz.getGenericInterfaces();
		System.out.println("clazz.getGenericInterfaces: ");
		for(Type t : ts1) {
			System.out.println(t);
		}
		System.out.println("\n");
		
		Class[] c1 = clazz.getInterfaces();
		System.out.println("clazz.getInterfaces:");
		for(Class c : c1) {
			System.out.println(c);
		}
		System.out.println("\n");
		
		System.out.println("superclass: " + clazz.getSuperclass() + "\n");*/
		
		Type t2 = clazz.getGenericSuperclass();
		System.out.println("getGenericSuperclass: " + t2);
	}

	

}
