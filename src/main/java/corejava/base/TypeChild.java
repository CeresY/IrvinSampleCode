package corejava.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TypeChild extends TypeFather {
	
	public static void main(String[] args) {
		TypeFather foo = new TypeChild();
		//foo.testC1();//���벻ͨ������Ϊ����ص�ʱ��ֻ�ǳ�ʼ����father����
		//foo.testF1();
		//System.out.println(TypeChild.class);
		try {
			Method m = foo.getClass().getMethod("testF1", null);
			m.invoke(null);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public void testC1() {
		System.out.println("Child-1");
	}
}
