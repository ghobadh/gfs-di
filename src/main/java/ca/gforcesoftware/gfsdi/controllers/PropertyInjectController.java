package ca.gforcesoftware.gfsdi.controllers;

import ca.gforcesoftware.gfsdi.services.GreetingService;

/**
 * @author gavinhashemi on 2024-10-01
 *
 * NOTE: This type of coding for implmenting IoC shold be avoid, as creating an object by property is NOT recommended
 */
public class PropertyInjectController {
    public GreetingService greetingService;

    public String getGreeting(){
        return greetingService.sayGreeting();
    }
}
