package bookstore.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
})

public interface WebDriverConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String baseURL();

    @Key("baseURI")
    @DefaultValue("https://demoqa.com")
    String baseURI();

    @Key("browser")
    @DefaultValue("CHROME")
    Browser browser();

    @Key("browserResolution")
    @DefaultValue("1920x1080")
    String browserResolution();

    @Key("remoteRun")
    @DefaultValue("false")
    Boolean remoteRun();

    @Key("webDriverHost")
    @DefaultValue("http://localhost:4444")
    String webDriverHost();

    @Key("pageLoadStrategy")
    @DefaultValue("eager")
    String pageLoadStrategy();

    @Key("webDriverTimeout")
    @DefaultValue("5000")
    int webDriverTimeout();


}
