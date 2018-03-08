package corejava.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvactionHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("正在执行方法：" + method);
		if(args != null) {
			System.out.println("执行参数：");
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
	 * 动态代理生成实例(默认构造器)
	 */
	public void test1() {
		MyInvactionHandler handler = new MyInvactionHandler();
		/*ModelReflct model = (ModelReflct)Proxy.newProxyInstance(
				ModelReflct.class.getClassLoader(), 
				new Class[]{ModelReflct.class}, 
				handler);
		model.info("动态代理", 1);*/
		
		ModelInterface model = (ModelInterface)Proxy.newProxyInstance(
				ModelInterface.class.getClassLoader(), 
				new Class[]{ModelInterface.class}, 
				handler);
		model.m2("接口");
	}
	
	/**
	 * 动态代理生成实例(指定构造器)
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
			model.info("动态代理", 2);*/
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
