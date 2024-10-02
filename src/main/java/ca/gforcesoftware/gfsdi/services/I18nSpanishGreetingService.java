package ca.gforcesoftware.gfsdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author gavinhashemi on 2024-10-02
 * We defined the qualifiter not with @Qualifier annotation but with defining in front of @Service annotation
 */

    //By adding "default" to @Profile, we don't need active profile in application.properties anymore.
    // without profile.active nor "default" in @Profile, spring will fail to choose which service profile has to select for the controller
    //NOTE, Spring.profile.active has higher priority than "default" in the @Profile annotation
@Profile({"ES", "default"})
@Service("i18nService")
public class I18nSpanishGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hola Mundo. Comesta?";
    }
}
