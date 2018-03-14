package web.spring.v20.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class LogAround implements MethodInterceptor{
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		log.info(mi.getArguments()[0] + " ��ʼ�������");
		try {
			Object result = mi.proceed();
			return result;
		}finally{
			log.info(mi.getArguments()[0] + " ������˽���");
		} 
	}

}
