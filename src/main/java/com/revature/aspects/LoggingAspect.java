package com.revature.aspects;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static Logger log = Logger.getLogger(LoggingAspect.class);
	
	@Before("within(com.revature.DAO.*)")
	public void logDAOArgs(JoinPoint jp) {
		String appStr = "";
		for (Object o : jp.getArgs()) {
			appStr += o.toString();
		}
		log.info(jp.getTarget() + " INVOKED " + jp.getSignature() + " | PARAMS: \n" + appStr);
		
	}
	
	@AfterReturning(pointcut = "within(com.revature.DAO.*)", returning = "ret")
	public void logDAOReturn(JoinPoint jp, Object ret) {
		log.info(jp.getSignature() + " RETURNED: " + ret);
	}
	
	@Before("within(com.revature.controllers.*)")
	public void logControllers(JoinPoint jp) {
		String appStr = "";
		for (Object o : jp.getArgs()) {
			appStr += o.toString();
		}
		log.info(jp.getTarget() + " INVOKED " + jp.getSignature() + " | PARAMS: \n" + appStr);
 	}
	
}
