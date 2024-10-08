package ca.gforcesoftware.gfsdi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

/**
 * @author gavinhashemi on 2024-10-08
 * <p>
 * Check GreetingServiceConfig for the explanation
 */

@ConfigurationProperties("gforce1")
public class GfsConstructorConfiguration {
    /*

     */
    private final String arg4;
    private final String arg5;
    private final String arg6;

    @ConstructorBinding
    public GfsConstructorConfiguration(String arg4, String arg5, String arg6) {
        this.arg4 = arg4;
        this.arg5 = arg5;
        this.arg6 = arg6;
    }

    public String getArg4() {
        return arg4;
    }

    public String getArg5() {
        return arg5;
    }

    public String getArg6() {
        return arg6;
    }
}
