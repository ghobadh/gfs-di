package ca.gforcesoftware.gfsdi.controllers;

import ca.gforcesoftware.gfsdi.services.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * @author gavinhashemi on 2024-10-02
 */
@Controller
public class I18NController {
    private final GreetingService greetingService;
    public I18NController(@Qualifier("i18nService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello() {
        return greetingService.sayGreeting();
    }
}
