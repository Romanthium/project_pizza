package com.example.projectpizza.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Pointcut("within(com.example.projectpizza.service..*) || within(com.example.projectpizza.controller..*)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.debug("Method {} started with args:{}", () -> joinPoint.getSignature().getName(),
                () -> Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "pointcut()", returning = "returnValue")
    public void logAfterReturning(JoinPoint joinPoint, Object returnValue) {
        logger.debug("Method {} completed with return value: {}", () -> joinPoint.getSignature().getName(), () -> returnValue);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error("Exception in {}.{}() with cause = {}", () -> joinPoint.getSignature().getDeclaringTypeName(),
                () -> joinPoint.getSignature().getName(), () -> e.getCause() != null ? e.getCause() : "NULL");
    }
}
