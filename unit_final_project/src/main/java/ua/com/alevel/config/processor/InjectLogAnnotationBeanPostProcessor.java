package ua.com.alevel.config.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import ua.com.alevel.config.annotations.InjectLog;

import java.lang.reflect.Field;

@Component
public class InjectLogAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final ApplicationContext context;

    public InjectLogAnnotationBeanPostProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectLog.class)) {
                Object loggerService = context.getBean("loggerService");
                field.setAccessible(true);
                try {
                    field.set(bean, loggerService);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
