package ua.com.alevel.aop.strategy.impl;

import org.apache.commons.collections4.MapUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import ua.com.alevel.aop.strategy.AspectExecutorStrategy;
import ua.com.alevel.service.SearchBookInfoService;
import ua.com.alevel.util.WebUtil;

import java.util.Map;

@Component
public class AspectExecutorStrategyImpl implements AspectExecutorStrategy {

    private final SearchBookInfoService searchBookInfoService;

    public AspectExecutorStrategyImpl(SearchBookInfoService searchBookInfoService) {
        this.searchBookInfoService = searchBookInfoService;
    }

    @Override
    public Object executeAspect(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        if (args != null) {
            if (args[0] instanceof Map<?,?>) {
                Map<String, Object> queryMap = (Map<String, Object>) args[0];
                if (MapUtils.isNotEmpty(queryMap)) {
                    queryMap.forEach((k, v) -> {
                        if (k.equals(WebUtil.PUBLISHER_PARAM)) {
                            Long id = (Long) v;
                            searchBookInfoService.process(WebUtil.PUBLISHER_PARAM, id);
                        }
                    });
                }
            }
        }
        return pjp.proceed();
    }
}
