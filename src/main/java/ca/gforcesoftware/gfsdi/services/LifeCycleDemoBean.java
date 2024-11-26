package ca.gforcesoftware.gfsdi.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author gavinhashemi on 2024-10-02
 */
@Component
public class LifeCycleDemoBean implements InitializingBean, DisposableBean , BeanNameAware
    , BeanFactoryAware, ApplicationContextAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("## BeanFactoryAware" );
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("## Bean Name is called:" +name);

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("## BeanFactoryAware");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("## InitializingBean");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("## ApplicationContextAware");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("## PostConstruct");
    }
    @PreDestroy
    public void preDestroy() {
        System.out.println("## PreDestroy");
    }

    public void beforeInit(){
        System.out.println("## BeforeInit -- called by bean post processor");
    }

    public void afterInit(){
        System.out.println("## AfterInit -- called by bean post processor");
    }
}
