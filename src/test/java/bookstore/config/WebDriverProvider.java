package bookstore.config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;

public class WebDriverProvider {

    private final WebDriverConfig wdConfig;
    public WebDriverProvider(WebDriverConfig wdConfig) {
        this.wdConfig = wdConfig;
    }

    public void setConfig(){
        Configuration.baseUrl = wdConfig.baseURL();
        RestAssured.baseURI = wdConfig.baseURI();
        Configuration.browserSize = wdConfig.browserResolution();
        Configuration.browser = wdConfig.browser().toString();

        if (wdConfig.remoteRun()) {
            Configuration.remote = wdConfig.webDriverHost();
        }

        Configuration.pageLoadStrategy = wdConfig.pageLoadStrategy();
        Configuration.timeout = wdConfig.webDriverTimeout();
    }
}

