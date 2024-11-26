package ca.gforcesoftware.gfsdi.services;

import org.springframework.stereotype.Service;

/**
 * @author gavinhashemi on 2024-10-01
 * As I expalied in PropertyInjectController, since we added @AutoWired , we need to tell to spring how instantiate the interface of GreetingService
 * and we do it with adding @serivce in the implementation class
 */

// Since we create a refactor class of this class, we commented in @Service in here,
// otherwise Spring detect TWO class which implement GreetingService interface and that cause an error
@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello Gargamel. This is instantiated by gfsdi.";
    }
}
