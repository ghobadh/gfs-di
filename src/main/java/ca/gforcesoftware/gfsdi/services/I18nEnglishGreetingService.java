package ca.gforcesoftware.gfsdi.services;

import ca.gforcesoftware.gfsdi.repositories.EnglishGreetingRepository;

/**
 * @author gavinhashemi on 2024-10-02
 */

//Similar to others we commented out both @Profile and @Service in here and then we add this service class in GreetingServiceConfig.
// IMPORTANT NOTE the name of Service we have "i18Service" will be the name of method in the @Bean in GreetingServiceConfig. The @Profile in GreetingServiceConfig will be the same as here
//@Profile("EN")
//@Service("i18nService")
public class I18nEnglishGreetingService implements GreetingService {

    private final EnglishGreetingRepository englishGreetingRepository;

    public I18nEnglishGreetingService(EnglishGreetingRepository englishGreetingRepository) {
        this.englishGreetingRepository = englishGreetingRepository;
    }

    @Override
    public String sayGreeting() {
        return "Hello Gragamel. How are you? - D'oh";
    }
}
