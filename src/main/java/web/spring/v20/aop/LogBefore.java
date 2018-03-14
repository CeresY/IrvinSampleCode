package web.spring.v20.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class LogBefore implements MethodBeforeAdvice {
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		log.info(args[0] + ": 之前开始审核数据");
		System.out.println();
	}

}
