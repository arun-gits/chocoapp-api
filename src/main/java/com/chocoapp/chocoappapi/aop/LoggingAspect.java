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

	private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("execution(* com.chocoapp.chocoappapi.service.ChocoService+.*(..))")
	public Object userAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

		log.info("logAround() is running!");
		log.info("Method Signature : " + joinPoint.getSignature().getName());
		log.info("Method arguments : " + Arrays.toString(joinPoint.getArgs()));

		log.info("Around before is running!");
		Object obj = joinPoint.proceed(); // continue on the intercepted method

		log.info("Around after is running!");

		log.info("******");
		return obj;
	}

}
