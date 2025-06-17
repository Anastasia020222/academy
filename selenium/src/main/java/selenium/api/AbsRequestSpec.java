package selenium.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class AbsRequestSpec {

    private static final String BASE_URL = System.getProperty("base.url");
    private final String token = "perm-0JDQvdCw0YHRgtCw0YHQuNGPX9Cb0YvRgdC10L3QutC+.NDQtMTQ=.ZG0Qk5IZy2cYJB024uAgj0kdF52IeR";

    protected final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
            .auth().oauth2(token);
}
