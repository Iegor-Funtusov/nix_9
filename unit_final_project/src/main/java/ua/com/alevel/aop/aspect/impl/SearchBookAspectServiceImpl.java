package ua.com.alevel.aop.aspect.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.com.alevel.aop.aspect.SearchBookAspectService;
import ua.com.alevel.aop.strategy.AspectExecutorStrategy;

@Aspect
@Component
public class SearchBookAspectServiceImpl implements SearchBookAspectService {

    public SearchBookAspectServiceImpl(AspectExecutorStrategy strategy) {
        this.strategy = strategy;
    }

    private final AspectExecutorStrategy strategy;

    @Override
    @Pointcut("execution(* ua.com.alevel.service.impl.PLPServiceImpl.search(..))")
    public void pointcut() { }

    @Override
    @Around(value = "pointcut()")
    public Object doAspect(ProceedingJoinPoint pjp) throws Throwable {
        return strategy.executeAspect(pjp);
    }
}
