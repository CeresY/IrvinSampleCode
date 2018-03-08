package corejava.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MysInvocationHandler implements InvocationHandler {
	
	private Object target;//模拟被代理的对象
	public void setTarget(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		PlayerUtil pu = new PlayerUtil();
		pu.method1();
		Object result = method.invoke(target, args);
		pu.method2();
		return result;
	}

}
