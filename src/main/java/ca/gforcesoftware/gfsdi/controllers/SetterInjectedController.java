package ca.gforcesoftware.gfsdi.controllers;

import ca.gforcesoftware.gfsdi.services.GreetingService;

/**
 * @author gavinhashemi on 2024-10-01
 *
 * This way , we are using setter to instatiate the object. It is controversial implementation
 */
public class SetterInjectedController {
    private GreetingService greetingService;

    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting(){
        return greetingService.sayGreeting();
    }
}
