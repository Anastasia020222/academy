package cucumber.selenide.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static cucumber.selenide.utils.AuthHelper.readTokenJson;

public class AbsRequestSpec {

    private static final String BASE_URL = System.getProperty("base.url");
    private final String token = "добавить токен";

    protected final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
            .auth().oauth2(token);
}
