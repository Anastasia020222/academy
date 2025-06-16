package rest.assured.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static rest.assured.utils.Token.extractToken;

public abstract class AbsServicesApi {

    private static final String BASE_URL = System.getProperty("base.url");
    private static final String pathToken = "src/main/resources/token.json";

    protected final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
            .auth().oauth2(extractToken(pathToken));
}
