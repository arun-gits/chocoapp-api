package com.chocoapp.chocoappapi.aop;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	//private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Around("execution(* com.chocoapp.chocoappapi.service.ChocoService+.*(..))")
	public Object userAdvice(ProceedingJoinPoint joinPoint) throws Throwable  {
		
		System.out.println("logAround() is running!");
		System.out.println("Method Signature : " + joinPoint.getSignature().getName());
		System.out.println("Method arguments : " + Arrays.toString(joinPoint.getArgs()));

		System.out.println("Around before is running!");
		Object obj = joinPoint.proceed(); // continue on the intercepted method
		System.out.println(obj);
		System.out.println("Around after is running!");

		System.out.println("******");
		return obj;
	}

}
