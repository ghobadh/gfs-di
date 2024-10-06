package ca.gforcesoftware.gfsdi.config;

import ca.gforcesoftware.gfsdi.services.ConstractorGreetingService;
import ca.gforcesoftware.gfsdi.services.PropertyGreetingService;
import ca.gforcesoftware.gfsdi.services.SetterGreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
