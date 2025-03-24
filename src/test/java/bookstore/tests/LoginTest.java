package bookstore.tests;

import bookstore.api.LoginApi;
import bookstore.components.LoginAddCookies;
import bookstore.models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bookstore.data.Path.PROFILE;
import static bookstore.data.TestData.USERNAME;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginTest extends TestBase {

    @Test
    @DisplayName("Логин, API и куки")
    public void loginTest(){

        LoginResponseModel loginResponse = new LoginApi().login();

        step("Добавление куки", () -> {
            LoginAddCookies cookies = new LoginAddCookies();
            cookies.addCookies(loginResponse);
        });

        step("Валидация логина на страницк магазина", () -> {
            open(PROFILE);
            $("#userName-value").shouldHave(text(USERNAME));
        });

    }

}
