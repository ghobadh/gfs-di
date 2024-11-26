package ca.gforcesoftware.gfsdi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author gavinhashemi on 2024-10-08
 */
@ConfigurationProperties("gforce")
@Configuration
public class GfsConfiguration {
    /*
    - In this class I add two annotation. One I mentioned this class is a configuration class with using @Configuration
    - with @ConfigurationProperties I define what group of properties spring should rearch and bind it to this class. In this example I search for any properties which start with "gforce"
    - *** As long as the name of fields in here match with the arguments in properties it will get picked
    - When I create the bean in the GreetingServiceConfig, I don't need to pass the exact name of property argument as spring expression. I just need to pass this Configuration class file as the argument class file in the Bean
                                @Bean
                                DummyDataSourceArgBind dummyDataSourceArgBind(GfsConfiguration gfsConfiguration) {
     in GreetingServiceConfig
     */
    private String arg1;
    private String arg2;
    private String arg3;

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }
}
