package ua.com.alevel.aop.strategy;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AspectExecutorStrategy {

    Object executeAspect(ProceedingJoinPoint pjp) throws Throwable;
}
