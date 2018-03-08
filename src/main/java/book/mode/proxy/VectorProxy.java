package book.mode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class VectorProxy implements InvocationHandler {
	private Object proxyObj;
	
	public VectorProxy(Object obj) {
		proxyObj = obj;
	}

	public static Object factory(Object obj) {
		Class cls = obj.getClass();
		//return cls.getClassLoader(),cls.getInterfaces(),
		return null;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("befor calling " + method.getName());
		if(args != null) {
			for(int i=0; i<args.length; i++) {
				System.out.print("args: " + args[i]);
			}
			System.out.println();
		}
		Object newObj = method.invoke(proxy, args);
		System.out.println("befor calling " + method.getName());
		return newObj;
	}

}
