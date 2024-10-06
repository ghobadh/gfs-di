package ca.gforcesoftware.gfsdi.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author gavinhashemi on 2024-10-06
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("Creating Prototype Bean!!!!!!!!!!");
    }

    public String getMyScope() {
        return "I'm a prototype bean";
    }
}
