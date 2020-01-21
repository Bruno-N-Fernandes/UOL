package br.com.uol.cotacoes.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetricAspect {

	@Autowired
	private Logger logger;

	@Around("execution(* br.com.uol.cotacoes.webrest.controller.*.*(..))")
	public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		
		Object proceed = joinPoint.proceed();

		long finalTime = (System.currentTimeMillis() - start);
		
		if(finalTime <= 3000 ){
			logger.info("Function controller {} with time {}", joinPoint.getSignature(), finalTime);
		} else {
			logger.error("Function controller {} with time {}", joinPoint.getSignature(), finalTime);
		}		
		
		return proceed;
	}
	
	@Around("execution(* br.com.uol.cotacoes.core.business.service.*.*(..))")
	public Object logAroundService(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		
		Object proceed = joinPoint.proceed();
		
		long finalTime = (System.currentTimeMillis() - start);
		
		if(finalTime < 1000){
			logger.info("Function service {}, arguments {} with time {}", joinPoint.getSignature(), joinPoint.getArgs(), finalTime);
		} else if(finalTime < 3000){
			logger.warn("Function service {}, arguments {} with time {}", joinPoint.getSignature(), joinPoint.getArgs(), finalTime);
		} else {
			logger.error("Function service {}, arguments {} with time {}", joinPoint.getSignature(), joinPoint.getArgs(), finalTime);
		}
		
		return proceed;
	}
	
	@Around("execution(* br.com.uol.cotacoes.webrest.helper.*.*(..))")
	public Object logAroundHelper(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		
		Object proceed = joinPoint.proceed();

		long finalTime = (System.currentTimeMillis() - start);
				
		if(finalTime > 500){			
			if(finalTime > 1500){
				logger.error("Function helper {} with time {}", joinPoint.getSignature(), finalTime);
			}
			logger.warn("Function helper {} with time {}", joinPoint.getSignature(), finalTime);
		}		
		
		return proceed;
	}

}
