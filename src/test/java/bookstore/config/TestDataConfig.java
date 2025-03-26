package bookstore.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:testData.properties"
})

public interface TestDataConfig extends Config {

    @Config.Key("USERNAME")
    @Config.DefaultValue("TossAntilles")
    String USERNAME();

    @Config.Key("PASSWORD")
    @Config.DefaultValue("!Toss1138")
    String PASSWORD();
}
