package rest.assured.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static rest.assured.services.JsonFileToString.getRequestBody;
import static rest.assured.utils.Path.REQUEST_TAGS;

public class TagsApi extends AbsServicesApi {

    private static final String TAG_PATH = "/api/tags";

    public Response createTag(String path) {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(TAG_PATH)
                .body(getRequestBody(path))
                .log().all()
                .when().post()
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response deleteTag(String idTags) {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(TAG_PATH + "/" + idTags)
                .log().all()
                .when().delete()
                .then()
                .log().all()
                .extract().response();
    }

    public Response getAllTags() {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(TAG_PATH)
                .body(getRequestBody(REQUEST_TAGS))
                .log().all()
                .when().get()
                .then()
                .statusCode(200)
                .extract().response();
    }
}
