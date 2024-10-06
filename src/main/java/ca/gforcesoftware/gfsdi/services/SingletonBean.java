package ca.gforcesoftware.gfsdi.services;

import org.springframework.stereotype.Component;

/**
 * @author gavinhashemi on 2024-10-06
 */
@Component
public class SingletonBean {
    public SingletonBean() {
        System.out.println("Creating SingletonBean");
    }

    public String getMyScope() {
        return "I'm a singleton bean";
    }
}
