package com.revature.aspects;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static Logger log = LogManager.getLogger("info");
	
	@Before("within(com.revature.DAO.*)")
	public void logDAOArgs(JoinPoint jp) {
		System.out.println("logDAOargs invoked");
		log.info(jp.getTarget() + " INVOKED " + jp.getSignature() + " | PARAMS: " + jp.getArgs());
	}
	
	@AfterReturning(pointcut = "within(com.revature.DAO.*)", returning = "ret")
	public void logDAOReturn(JoinPoint jp, Object ret) {
		System.out.println("logDAOargs invoked");
		log.info(jp.getSignature() + " RETURNED: " + ret);
	}
	
}
