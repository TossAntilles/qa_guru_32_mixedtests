package bookstore.tests;

import bookstore.api.LoginApi;
import bookstore.components.LoginAddCookies;
import bookstore.models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest extends TestBase {

    @Test
    public void loginTest(){

        LoginResponseModel loginResponse = new LoginApi().login();
        LoginAddCookies cookies = new LoginAddCookies();
        cookies.addCookies(loginResponse);

        open("https://demoqa.com/profile");
        $("#userName-value").shouldHave(text("TossAntilles"));
    }

}
