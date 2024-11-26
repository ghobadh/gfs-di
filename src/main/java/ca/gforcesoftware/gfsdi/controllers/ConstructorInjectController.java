package ca.gforcesoftware.gfsdi.controllers;

import ca.gforcesoftware.gfsdi.services.ConstractorGreetingService;
import ca.gforcesoftware.gfsdi.services.GreetingService;
import ca.gforcesoftware.gfsdi.services.GreetingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * @author gavinhashemi on 2024-10-01
 * In order spring manage the beans, we add annotation @Controller to the class, so we can call it, spring trys to initiate it
 * PLEASE read the comment below
 */
@Controller
public class ConstructorInjectController {

    // As we mentioned in the main method, we need to use private final for the injected object
    //When we add the 'Controller' annotation, we need to add another annotation , so spring start to initiate the object and that is 'Autowired' annotation. In addition,
    // we need to add 'Service' annotation to the class which implement this interface "GreetingServiceImpl". Otherwise, when the app start working, it will fail because
    // Unlike PropertyInjectController and SetterInjectController, since we use constructor to initiate the bean, spring can detect the object so adding @Autowired
    //becomes OPTIONAL when use constructor for our controller
    //@Autowired <-- commenting this is totally fine, and spring knows how to initiate it
    private final GreetingService greetingService;

    //Since we have several Services which all of them implemented the GreetingService Interface, now Spring does not know which one
    //has to inject into the controller. In here there are two annotation are useful , we can use either of them @Qualifier and @Primary
    //When we use @Qualifier , we are explicitly telling to spring which service has to be injected to this controller
    //Remember we use lower case the first character of service class when we put it in qualifier
    public ConstructorInjectController(@Qualifier("constractorGreetingService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting(){
        return greetingService.sayGreeting();
    }

}
