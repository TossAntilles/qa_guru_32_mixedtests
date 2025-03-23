package bookstore.components;

import bookstore.models.LoginResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginAddCookies {
    
    public LoginResponseModel addCookies(LoginResponseModel lr) {

        open("https://demoqa.com/images/Toolsqa.jpg");
        getWebDriver().manage().addCookie(new Cookie("userID", lr.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", lr.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", lr.getToken()));

        return lr;
    }
    
}
