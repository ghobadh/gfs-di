package ca.gforcesoftware.gfsdi.config;

import ca.gforcesoftware.gfsdi.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * @author gavinhashemi on 2024-10-06
 */
@Configuration
public class GreetingServiceConfig {

    //In here we explicitly define this class as bean, remember @Bean is used in @Configuration annotation. In here we don't need @Service in constructorGreetingService anymore and that it why
    // we commented out over there.
    @Bean
    ConstractorGreetingService constractorGreetingService() {
        return new ConstractorGreetingService();
    }

    //we do for other service classes as well.
    @Bean
    SetterGreetingService setterGreetingService() {
        return new SetterGreetingService();
    }

    //we do for other service classes as well.
    @Bean
    PropertyGreetingService propertyGreetingService() {
        return new PropertyGreetingService();
    }


    // Since we removed the @Primary in the service class itself, we add in here to indicate this class is a primary profile
    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }


    // IMPORTANT NOTE the name of Service we have "i18Service" will be the name of method in the @Bean in GreetingServiceConfig. The @Profile in GreetingServiceConfig will be the same as here
    @Profile("EN")
    @Bean("i18nService")
    I18nEnglishGreetingService i18nService() {
        return new I18nEnglishGreetingService();
    }


    //For the spanish profile we do the same
    // IMPORTANT NOTE the name of Service we have "i18Service" used for the @Bean in GreetingServiceConfig as we removed @Service("i18nSerive") from the service class . The @Profile in GreetingServiceConfig will be the same as here
    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18nSpanishGreetingService i18nSpanishService() {
        return new I18nSpanishGreetingService();
    }

}
