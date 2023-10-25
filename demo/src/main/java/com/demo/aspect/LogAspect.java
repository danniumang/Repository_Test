
package com.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component

@Aspect
public class LogAspect {

	@Pointcut("execution(* com.demo.service.*.*(..))")
	public void pc1() {
	}

	@Before("pc1()")
	public void before(JoinPoint jp) {
		System.out.println("before " + jp.getSignature().getName());
	}

	@After("pc1()")
	public void after(JoinPoint jp) {
		System.out.println("after " + jp.getSignature().getName());
	}

	@AfterReturning(value = "pc1()", returning = "result")
	public void afterReturning(JoinPoint jp, Object result) {
		System.out.println("afterReturing" + jp.getSignature().getName());
	}

	@AfterThrowing(value = "pc1()", throwing = "e")
	public void afterThrowing(JoinPoint jp, Exception e) {
		System.out.println("afterThrowiing" + jp.getSignature().getName());
	}

	@Around("pc1()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("around " + pjp.getSignature().getName());
		return pjp.proceed();

	}
}
