package ca.gforcesoftware.gfsdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author gavinhashemi on 2024-10-02
 */
@Profile("EN")
@Service("i18nService")
public class I18nEnglishGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello Gragamel. How are you?";
    }
}
