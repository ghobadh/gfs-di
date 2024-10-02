package ca.gforcesoftware.gfsdi.services;

import org.springframework.stereotype.Service;

/**
 * @author gavinhashemi on 2024-10-01
 * As I expalied in PropertyInjectController, since we added @AutoWired , we need to tell to spring how instantiate the interface of GreetingService
 * and we do it with adding @serivce in the implementation class
 *
 *
 *  This class is a refactor of GreetingSericeImpl
 */
@Service
public class ConstractorGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello Gargamel. This is instantiated by gfsdi --constructor--.";
    }
}
