package ua.com.alevel.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public interface SearchBookAspectService {

    void pointcut();

    Object doAspect(ProceedingJoinPoint pjp) throws Throwable;
}
