package ca.gforcesoftware.gfsdi.controllers;

import ca.gforcesoftware.gfsdi.services.GreetingService;
import org.springframework.stereotype.Controller;

/**
 * @author gavinhashemi on 2024-10-01
 */
@Controller
public class MyController {


    /*
    We are adding a controller without mentioning qualifier. Since PrimaryGreetingService has @Primary, this service will be chosen to use for injected bean
     */
    private final GreetingService greetingService;
    /* We add constructor */

    public MyController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello(){
        System.out.println("Hello World");

        return "Hello Gargamel";
    }

    public String getGreeting(){
        return greetingService.sayGreeting();
    }
}
