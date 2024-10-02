package ca.gforcesoftware.gfsdi.services;

import org.springframework.stereotype.Service;

/**
 * @author gavinhashemi on 2024-10-02
 */
@Service
public class PropertyGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello Gargamel. This is instantiated by gfsdi --property--.";
    }
}
