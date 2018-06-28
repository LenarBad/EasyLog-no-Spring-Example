package io.lenar.examples;

import io.lenar.easy.log.EasyLoggerNoSpring;
import io.lenar.easy.log.annotations.LogIt;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyLogger extends EasyLoggerNoSpring {

    @Around(CLASS_LEVEL_LOGIT_POINTCUT)
    public Object classLog(ProceedingJoinPoint jp, LogIt annotation) throws Throwable {
        return logIt(jp, annotation);
    }

    @Around(METHOD_LEVEL_LOGIT_POINTCUT)
    public Object methodLog(ProceedingJoinPoint jp, LogIt annotation) throws Throwable {
        return logIt(jp, annotation);
    }

}
