package kr.or.ddit.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect // Aspect = Advice + Pointcut
public class LoggingAdvice {
    @Pointcut("execution(* kr.or.ddit..service.*.*(..))") // Pointcut
    public void dummy() {}

    // @Before("dummy()") // Advice
    public void before(JoinPoint joinPoint) { // Pointcut
        Object target = joinPoint.getTarget();
        String targetName = target.getClass().getName();
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        long start = System.currentTimeMillis();
        log.info("======{}.{} 메소드 실행 전, 전달 인자 : {}=================================", targetName, methodName, Arrays.toString(args));
    }
    
    // @AfterReturning(pointcut = "dummy()", returning = "resultValue") // Advice
    public void after(Object resultValue) { // Pointcut
        long end = System.currentTimeMillis();
        log.info("----------반환 객체 : {}-----------------------------", resultValue);
    }

    @Around("dummy()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String targetName = target.getClass().getName();
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        try{
            // before
            log.info("======{}.{} 메소드 실행 전, 전달 인자 : {}=================================", targetName, methodName, Arrays.toString(args));
            Object resultValue = joinPoint.proceed(args);
            // after returnning
            log.info("----------반환 객체 : {}-----------------------------", resultValue);
            return resultValue;
        } catch (Throwable e) {
            // after throwing
            log.error(e.getMessage(), e);
            throw e;
        }

    }
}
