package bookstore.config;

import org.aeonbits.owner.Config;

public interface EnvironmentConfig extends Config {

    @Config.Key("ENVIRONMENT")
    @Config.DefaultValue("qaguru")
    String ENVIRONMENT();
}
