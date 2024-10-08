package ca.gforcesoftware.gfsdi.config;

import ca.gforcesoftware.gfsdi.datasource.DummyDataSource;
import ca.gforcesoftware.gfsdi.repositories.EnglishGreetingRepository;
import ca.gforcesoftware.gfsdi.repositories.EnglishGreetingRepositoryImpl;
import ca.gforcesoftware.gfsdi.services.*;
import com.gargamel.pets.PetService;
import com.gargamel.pets.PetServiceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * @author gavinhashemi on 2024-10-06
 */
@PropertySource("classpath:datasource.properties")
@ImportResource("classpath:gfsdi-config.xml")
@Configuration
public class GreetingServiceConfig {


    //In here we explicitly define this class as bean, remember @Bean is used in @Configuration annotation. In here we don't need @Service in constructorGreetingService anymore and that it why
    // I commented out over there.


    /*
        In order to use external property file we these steps
        1- I created Data source file called DummyDataSource under datasource package. I created exactly those properties I want to load them
        2- I created datasource.properties files under resources and I added those properites I want to have in Datasource object
        3- in this file first I add @PropertySource annotation in the class level and with classpath I put the name of source file
        4- I created a bean as below to pass all properties into the datasource object
        5- I used Spring expression "${property_name}" along with @Value annotation to pass the date to the object parameters.
     */
    @Bean
    DummyDataSource dummyDataSource(@Value("${gforce.username}") String username,
                                    @Value("${gforce.password}") String password,
                                    @Value("${gforce.jdbcurl}") String jdburl) {
        DummyDataSource dummyDataSource = new DummyDataSource();
        dummyDataSource.setUsername(username);
        dummyDataSource.setPassword(password);
        dummyDataSource.setJdbcURL(jdburl);
        return dummyDataSource;
    }

    /*
    in this branch we removed the @Bean, because we did in the old way using XML
    1- we created gfsdi-config.xml
    2- we defined the bean over ther
    3- we used @ImportResource to give the class path of the bean configuration
    4- we commented out the @Bean in below
     */

    //@Bean
    //ConstractorGreetingService constractorGreetingService() {
    //    return new ConstractorGreetingService();
    //}

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

    @Bean
    EnglishGreetingRepository englishGreetingRepository() {
        return new EnglishGreetingRepositoryImpl();
    }


    // IMPORTANT NOTE the name of Service we have "i18Service" will be the name of method in the @Bean in GreetingServiceConfig. The @Profile in GreetingServiceConfig will be the same as here
    @Profile("EN")
    @Bean("i18nService")
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }


    //For the spanish profile we do the same
    // IMPORTANT NOTE the name of Service we have "i18Service" used for the @Bean in GreetingServiceConfig as we removed @Service("i18nSerive") from the service class . The @Profile in GreetingServiceConfig will be the same as here
    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18nSpanishGreetingService i18nSpanishService() {
        return new I18nSpanishGreetingService();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("dog");
    }

    @Profile({"cat"})
    @Bean
    PetService catPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("cat");
    }


    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

}
