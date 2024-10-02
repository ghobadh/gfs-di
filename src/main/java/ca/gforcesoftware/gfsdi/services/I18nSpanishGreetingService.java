package ca.gforcesoftware.gfsdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author gavinhashemi on 2024-10-02
 * We defined the qualifiter not with @Qualifier annotation but with defining in front of @Service annotation
 */
@Profile("ES")
@Service("i18nService")
public class I18nSpanishGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hola Mundo. Comesta?";
    }
}
