package ca.gforcesoftware.gfsdi.controllers;

import ca.gforcesoftware.gfsdi.services.GreetingService;
import ca.gforcesoftware.gfsdi.services.SetterGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * @author gavinhashemi on 2024-10-01
 *
 * This way , we are using setter to instatiate the object. It is controversial implementation
 * In order spring manage the beans, we add annotation @Controller to the class, so we can call it, spring trys to initiate it
 */
@Controller
public class SetterInjectedController {
    //Since we have several Services which all of them implemented the GreetingService Interface, now Spring does not know which one
    //has to inject into the controller. In here there are two annotation are useful , we can use either of them @Qualifier and @Primary
    //When we use @Qualifier , we are explicitly telling to spring which service has to be injected to this controller
    //Remember we use lower case the first character of service class when we put it in qualifier
    @Qualifier("setterGreetingService")
    //When we add the 'Controller' annotation, we need to add another annotation , so spring start to initiate the object and that is 'Autowired' annotation. In addition,
    // we need to add 'Service' annotation to the class which implement this interface "GreetingServiceImpl". Otherwise, when the app start working, it will fail because
    // It cannot know, how to implement the interface GreetingService when it see @Autowired
    @Autowired
    private GreetingService greetingService;

    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting(){
        return greetingService.sayGreeting();
    }
}
