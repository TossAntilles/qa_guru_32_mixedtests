package bookstore.tests;

import bookstore.api.LoginApi;
import bookstore.components.LoginAddCookies;
import bookstore.config.TestDataConfig;
import bookstore.config.WebPathConfig;
import bookstore.helpers.Attach;
import bookstore.models.LoginResponseModel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginTest extends TestBase {

    private static final WebPathConfig PATH = ConfigFactory.create(WebPathConfig.class, System.getProperties());
    private static final TestDataConfig USER = ConfigFactory.create(TestDataConfig.class, System.getProperties());


    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @DisplayName("Логин, API и куки")
    public void loginTest() {

        LoginResponseModel loginResponse = new LoginApi().login();

        step("Добавление куки", () -> {
            LoginAddCookies cookies = new LoginAddCookies();
            cookies.addCookies(loginResponse);
        });

        step("Валидация логина на страницк магазина", () -> {
            open(PATH.PROFILE());
            $("#userName-value").shouldHave(text(USER.USERNAME()));
        });

    }

}
