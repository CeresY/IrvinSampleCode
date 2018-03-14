package corejava.reflect.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * ʹ�÷��ͼ򻯴���
 * @author yangzhan
 *
 */
public class GenericTest {
	private Class entityClass;
	public static void main(String[] args) {
		GenericTest foo = new GenericTest();
		foo.test1();
	}

	public GenericTest() {
		Type genType = getClass().getGenericSuperclass();
		Type[]  params = ((ParameterizedType)genType).getActualTypeArguments();
		entityClass = (Class)params[0];
	}
	
	public void test1() {
		System.out.println();
		new ArrayList();
	}

}
