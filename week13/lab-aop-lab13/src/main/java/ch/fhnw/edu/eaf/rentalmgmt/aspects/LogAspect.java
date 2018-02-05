package ch.fhnw.edu.eaf.rentalmgmt.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	private static final Logger log = Logger.getLogger(LogAspect.class);
			 
	@Around("execution(* findAll(..))")
	public Object interceptFindAll(ProceedingJoinPoint p) throws Throwable {
		String target = p.getTarget().toString();
		log.error(String.format("findAll => %s just got called!", target));
		Object result = p.proceed();
		String type = result.getClass().getTypeName();
		log.error(String.format("Result of findAll method is of type %s", type));
		return result;
	}
	
	//@Around("within(*..eaf..*.*)")
	@Around("execution(* *..eaf..*.*(..))")
	public Object traceAll(ProceedingJoinPoint p) throws Throwable {
		String target = p.getTarget().toString();
		log.error(String.format("Method in ch.fhnw.edu.eaf got called: %s", target));
		return p.proceed();
	}
	
	@Before("execution(* *..rentalmgmt..*Controller.*(..)) && args(param)")
	public void tracePageCrl() throws Throwable {
		log.info("Controller called with param: ");
	}
}
