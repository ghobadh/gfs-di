package ca.gforcesoftware.gfsdi.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

/**
 * @author gavinhashemi on 2024-10-02
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String  beanName) throws BeansException {
        if (bean instanceof LifeCycleDemoBean) {
            ((LifeCycleDemoBean) bean).beforeInit();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LifeCycleDemoBean) {
            ((LifeCycleDemoBean) bean).afterInit();
        }
        return bean;
    }
}
