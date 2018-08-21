package io.lenar.examples.log;

import io.lenar.easy.log.EasyLoggerNoSpring;
import io.lenar.easy.log.annotations.LogIt;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyLogger extends EasyLoggerNoSpring {

    @Around("execution(* *(..)) && @within(annotation)")
    public Object classLog(ProceedingJoinPoint jp, LogIt annotation) throws Throwable {
        return logItClassLevel(jp, annotation);
    }

    @Around("execution(* *(..)) && @annotation(annotation)")
    public Object methodLog(ProceedingJoinPoint jp, LogIt annotation) throws Throwable {
        return logItMethodLevel(jp, annotation);
    }

    @AfterThrowing(pointcut = "execution(* *(..)) && @within(annotation)", throwing = "e")
    public void classExceptionLog(JoinPoint jp, LogIt annotation, Throwable e) {
        logExceptionClassLevel(jp, annotation, e);
    }

    @AfterThrowing(pointcut = "execution(* *(..)) && @annotation(annotation)", throwing = "e")
    public void methodExceptionLog(JoinPoint jp, LogIt annotation, Throwable e) {
        logExceptionMethodLevel(jp, annotation, e);
    }

}
