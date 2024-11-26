package ca.gforcesoftware.gfsdi.config;

import ca.gforcesoftware.gfsdi.datasource.DummyDataSource;
import ca.gforcesoftware.gfsdi.datasource.DummyDataSourceArg;
import ca.gforcesoftware.gfsdi.datasource.DummyDataSourceArgBind;
import ca.gforcesoftware.gfsdi.datasource.DummyDataSourceArgConstructorBind;
import ca.gforcesoftware.gfsdi.repositories.EnglishGreetingRepository;
import ca.gforcesoftware.gfsdi.repositories.EnglishGreetingRepositoryImpl;
import ca.gforcesoftware.gfsdi.services.*;
import com.gargamel.pets.PetService;
import com.gargamel.pets.PetServiceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * @author gavinhashemi on 2024-10-06
 */
@EnableConfigurationProperties(GfsConstructorConfiguration.class)
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
        6- If we want to use the program arguments of the application we pass like this
            --gforce.password=passwordFromCmdLine
        7- If we want to use Environment Arguments we add like this
            GFORCE_USERNAME=UserNameFromEnvVar1

            Note:we need to put all chars in capital so spring will pick them up as exact lower cases in the datasource.properites and/or program arguments
        8- The file "application.properties" is a default file in Spring Boot so I don't need to add explicitly in @PropertySource annotation. Therefore, when I added gforce.username it is picked up from over there. Note that ,
        I had to remove both command line and environment variable in order the application picks up the property from application.properties files. (check HELP.md for the property priorities)

        9- Spring boot can use default properties profile files such as "application-dev.properties" or "application-qa.properties" in the resources folder. I just need to add the name of the profile in the "application.properties" like this
                    spring.profiles.active=cat,EN,qa
           In here we aeed the "qs" as an active profile in spring and the file "application-qa.properties" will be picked up automatically. I created another dummy data source as "dummyDataSourceArg" for this propose

     */
    @Bean
    DummyDataSourceArg dummyDataSourceArg(@Value("${gforce.arg1}") String arg1,
                                          @Value("${gforce.arg2}") String arg2,
                                          @Value("${gforce.arg3}") String arg3) {
        DummyDataSourceArg dummyDataSourceArg = new DummyDataSourceArg();
        dummyDataSourceArg.setArgument1(arg1);
        dummyDataSourceArg.setArgument2(arg2);
        dummyDataSourceArg.setArgument3(arg3);
        return dummyDataSourceArg;
    }

    /*
    In this change I used a class constructor as immutable object to make sure the date in the properties will not change. These are my steps
    1- I created a class file GfsConstuctoConfiguration file which has all parameters as final and one class constructor for it
    2- I added @ConstructorBinding  annotation to instruct spring that this constructor for binging
    3- I added @EnableConfigurationProperties(GfsConstructorConfiguration.class) to this file in the class level so to instruct spring that the GfsConstructorConfiguration will be used as proprety file reader
    4- I added this bean and popluated the properties in it so when I called it in contrext file (GfsDiApplication) the object works properly
    5- Similar to class binding no need to associate any exact property name into the method argument
     */
    @Bean
    DummyDataSourceArgConstructorBind dummyDataSourceArgConstructorBind(GfsConstructorConfiguration gfsConstructorConfiguration) {
        DummyDataSourceArgConstructorBind dummyDataSourceArgConstructorBind = new DummyDataSourceArgConstructorBind();
        dummyDataSourceArgConstructorBind.setArgument4(gfsConstructorConfiguration.getArg4());
        dummyDataSourceArgConstructorBind.setArgument5(gfsConstructorConfiguration.getArg5());
        dummyDataSourceArgConstructorBind.setArgument6(gfsConstructorConfiguration.getArg6());

        return dummyDataSourceArgConstructorBind;
    }


    //Binding arguments
    @Bean
    DummyDataSourceArgBind dummyDataSourceArgBind(GfsConfiguration gfsConfiguration) {
        DummyDataSourceArgBind dummyDataSourceArgBind = new DummyDataSourceArgBind();
        dummyDataSourceArgBind.setArgument1(gfsConfiguration.getArg1());
        dummyDataSourceArgBind.setArgument2(gfsConfiguration.getArg2());
        dummyDataSourceArgBind.setArgument3(gfsConfiguration.getArg3());
        return dummyDataSourceArgBind;
    }


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
    1- I created gfsdi-config.xml
    2- I defined the bean over ther
    3- I used @ImportResource to give the class path of the bean configuration
    4- I commented out the @Bean in below
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
