package ca.gforcesoftware.gfsdi.services;

/**
 * @author gavinhashemi on 2024-10-01
 * As I expalied in PropertyInjectController, since we added @AutoWired , we need to tell to spring how instantiate the interface of GreetingService
 * and we do it with adding @serivce in the implementation class
 *
 *
 *  This class is a refactor of GreetingSericeImpl
 */

//We remove both @Service and @Primary in here and add this service class as bea in GreetingServiceConfigclass. OVer there, we tell spring that this is a primary service class
//@Service
//@Primary
public class PrimaryGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello Gargamel. This is instantiated by gfsdi --Primary BEAN--.";
    }
}
