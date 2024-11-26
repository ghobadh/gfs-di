package ca.gforcesoftware.gfsdi.services;

/**
 * @author gavinhashemi on 2024-10-02
 * We defined the qualifiter not with @Qualifier annotation but with defining in front of @Service annotation
 */

    //By adding "default" to @Profile, we don't need active profile in application.properties anymore.
    // without profile.active nor "default" in @Profile, spring will fail to choose which service profile has to select for the controller
    //NOTE, Spring.profile.active has higher priority than "default" in the @Profile annotation

//Similar to others we commented out both @Profile and @Service in here and then we add this service class in GreetingServiceConfig.
// IMPORTANT NOTE the name of Service we have "i18Service" will be the name of method in the @Bean in GreetingServiceConfig. The @Profile in GreetingServiceConfig will be the same as here
//@Profile({"ES", "default"})
//@Service("i18nService")
public class I18nSpanishGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hola Mundo. Comesta?";
    }
}
