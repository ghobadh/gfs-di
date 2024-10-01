package ca.gforcesoftware.gfsdi.services;

/**
 * @author gavinhashemi on 2024-10-01
 */
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello Gargamel. This is instantiated by gfsdi.";
    }
}
