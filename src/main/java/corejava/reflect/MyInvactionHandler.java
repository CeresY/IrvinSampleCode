package corejava.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvactionHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("����ִ�з�����" + method);
		if(args != null) {
			System.out.println("ִ�в�����");
			for(Object obj : args) {
				System.out.println(obj);
			}
		}
		//method.invoke(proxy, args);
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyInvactionHandler foo = new MyInvactionHandler();
		foo.test1();
	}
	
	/**
	 * ��̬��������ʵ��(Ĭ�Ϲ�����)
	 */
	public void test1() {
		MyInvactionHandler handler = new MyInvactionHandler();
		/*ModelReflct model = (ModelReflct)Proxy.newProxyInstance(
				ModelReflct.class.getClassLoader(), 
				new Class[]{ModelReflct.class}, 
				handler);
		model.info("��̬����", 1);*/
		
		ModelInterface model = (ModelInterface)Proxy.newProxyInstance(
				ModelInterface.class.getClassLoader(), 
				new Class[]{ModelInterface.class}, 
				handler);
		model.m2("�ӿ�");
	}
	
	/**
	 * ��̬��������ʵ��(ָ��������)
	 */
	public void test2() {
		try{
			MyInvactionHandler handler = new MyInvactionHandler();
			Class clazz = Proxy.getProxyClass(ModelReflct.class.getClassLoader(), 
					new Class[]{ModelReflct.class});
			Constructor cons = clazz.getConstructor(new Class[]{String.class});
			ModelReflct model1 = (ModelReflct)cons.newInstance("zd-1");
			model1.info();
			/*ModelReflct model = (ModelReflct)Proxy.newProxyInstance(ModelReflct.class.getClassLoader(), 
					new Class[]{ModelReflct.class}, handler);
			model.info("��̬����", 2);*/
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
