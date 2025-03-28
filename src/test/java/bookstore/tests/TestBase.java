package bookstore.tests;

import bookstore.config.VideoRecordingProvider;
import bookstore.config.WebDriverConfig;
import bookstore.config.WebDriverProvider;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class TestBase {

    @BeforeAll
    @Step("Подготовка тестового окружения")
    static void beforeAll(){

        System.getProperty("ENVIRONMENT", "qaguru");
        //System.setProperty("ENVIRONMENT", "qaguru");

        final WebDriverConfig wdConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        final VideoRecordingProvider vidConfig = new VideoRecordingProvider();

        WebDriverProvider wdProvider = new WebDriverProvider(wdConfig);
        wdProvider.setConfig();
        vidConfig.setCapabilities();

    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void closeWD() {
        Selenide.closeWebDriver();
    }

}