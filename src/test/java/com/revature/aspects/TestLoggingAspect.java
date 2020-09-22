package com.revature.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestLoggingAspect {
	
	@Before("execution(* *.test*(..))")
	public void displayTestMethod(JoinPoint jp) {
		System.out.println(jp.getTarget() + " running " + jp.getSignature());
	}
	
}
