package bookstore.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:paths.properties"
})

public interface WebPathConfig extends Config {

    //API

    @Key("LOGIN")
    @DefaultValue("/Account/v1/Login")
    String LOGIN();

    @Key("BOOKSTORE")
    @DefaultValue("/BookStore/v1/Book")
    String BOOKSTORE();

    @Key("BOOKSTORES")
    @DefaultValue("/BookStore/v1/Books")
    String BOOKSTORES();

    ;

    @Key("ACCOUNT")
    @DefaultValue("/Account/v1/User/")
    String ACCOUNT();

    //WEB

    @Key("PROFILE")
    @DefaultValue("/profile")
    String PROFILE();

}
