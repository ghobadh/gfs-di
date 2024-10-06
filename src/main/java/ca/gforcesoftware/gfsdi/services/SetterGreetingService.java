package ca.gforcesoftware.gfsdi.services;

/**
 * @author gavinhashemi on 2024-10-02
 */
// We remove @Service annotation because will call this through GreetingServiceConfig
//@Service
public class SetterGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello Gargamel. This is instantiated by gfsdi --setter--.";
    }
}
