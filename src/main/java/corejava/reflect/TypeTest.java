package corejava.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TypeTest {
	public static void main(String[] args) {
		TypeTest foo = new TypeTest();
		foo.test5();
	}
	
	public void test1() {
		List<Double> list = new ArrayList<Double>();
		ParameterizedType type = (ParameterizedType) list.getClass().getGenericSuperclass();
		Type[] args = type.getActualTypeArguments();
		for(Type obj : args) {
			System.out.println((Class)obj);
		}
	}
	
	public void test2() {
		try {
			Method m = ModelReflct.class.getMethod("info", String.class);
			System.out.println(m.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void test3() {
		try {
			//使用默认构造器
			Class<?> clazz = Class.forName("corejava.reflect.ShowMethods");
			Object sm = clazz.newInstance();
			//mr.info();
			
			//String[] s = new String[]{""};
			//使用指定构造器
			Class<?> clazz2 = Class.forName("corejava.reflect.ModelReflct");
			Constructor c = clazz2.getConstructor(new Class[]{String.class, Integer.class});
			Object mr = c.newInstance("parameter1", 2);
			
			//调用public方法
			Method m1 = clazz2.getMethod("info", String.class);
			m1.invoke(mr, "haha");
			
			//调用private方法
			Method m2 = clazz2.getDeclaredMethod("privacy", String.class);
			m2.setAccessible(true);
			m2.invoke(mr, "测试私有");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 操作字段
	 */
	public void test4() {
		ModelReflct mr = new ModelReflct("test Field");
		Class clazz = ModelReflct.class;
		try {
			Field f1 = clazz.getDeclaredField("p1");
			f1.setAccessible(true);
			f1.set(mr, "param1-string");
			
			Field f2 = clazz.getDeclaredField("p2");
			f2.setAccessible(true);
			f2.set(mr, 20);
			System.out.println(mr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用反射动态生成数组
	 */
	public void test5() {
		Object array = Array.newInstance(String.class, 10);
		Array.set(array, 0, "Jau");
		Array.set(array, 3, "Apri");
		Object obj1 = Array.get(array, 0);
		Object obj2 = Array.get(array, 3);
		Object obj3 = Array.get(array, 1);
		System.out.println(obj1);
		System.out.println(obj2);
		System.out.println(obj3);
	}
}
