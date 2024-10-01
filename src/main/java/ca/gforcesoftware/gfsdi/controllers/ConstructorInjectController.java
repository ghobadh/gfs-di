package ca.gforcesoftware.gfsdi.controllers;

import ca.gforcesoftware.gfsdi.services.GreetingService;
import ca.gforcesoftware.gfsdi.services.GreetingServiceImpl;

/**
 * @author gavinhashemi on 2024-10-01
 */
public class ConstructorInjectController {

    // As we mentioned in the main method, we need to use private final for the injected object
    private final GreetingService greetingService;

    public ConstructorInjectController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting(){
        return greetingService.sayGreeting();
    }

}
