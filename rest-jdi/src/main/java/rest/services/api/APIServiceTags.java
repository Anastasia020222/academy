package rest.services.api;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("${domain}")
@Header(name = "Authorization", value = "Bearer //указать токен")
public class APIServiceTags {

    private static final String BASE_PATH = "/api/tags";

    @ContentType(JSON)
    @POST(BASE_PATH)
    public static RestMethod createTag;

    @ContentType(JSON)
    @GET(BASE_PATH + "/{id}/tags")
    @QueryParameter(name = "fields", value = "text,id")
    public static RestMethod getListTagsTask;

    @ContentType(JSON)
    @GET(BASE_PATH)
    @QueryParameter(name = "fields", value = "id,name")
    public static RestMethod getAllTags;

    @ContentType(JSON)
    @DELETE(BASE_PATH + "/{id}")
    public static RestMethod deleteTag;
}
