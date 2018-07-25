package corejava.reflect.generic;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GenericReflectTest {

	@Test
	public void test1() {
		List<String> list = new ArrayList<>();
		TypeVariable<?>[] type = (TypeVariable<?>[]) list.getClass().getTypeParameters();
		for(TypeVariable t : type) {
			System.out.println(t);
		}
	}
	
	@Test
	public void test2() {
		List<String> list = new ArrayList<>();
		Class clazz = list.getClass();
		Type type = clazz.getGenericSuperclass();
		System.out.println(type+"\n");
		
		Type[] types = clazz.getGenericInterfaces();
		for(Type t : types) {
			System.out.println(t);
		}
	}

}
