package corejava.reflect.proxy;

import java.lang.reflect.Proxy;

public class MyProxyFactory {
	public static Object getProxy(Object target) {
		MysInvocationHandler handler = new MysInvocationHandler();
		handler.setTarget(target);
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				handler);
	}
}
