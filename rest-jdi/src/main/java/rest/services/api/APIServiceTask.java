package rest.services.api;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("${domain}")
@Header(name = "Authorization", value = "Bearer //указать токен")
public class APIServiceTask {

    private static final String BASE_PATH = "/api/issues";

    @ContentType(JSON)
    @QueryParameter(name = "fields", value = "$type,id,idReadable,summary,description")
    @GET(BASE_PATH)
    public static RestMethod getTaskList;

    @ContentType(JSON)
    @QueryParameter(name = "fields", value = "id,idReadable,summary,description")
    @POST(BASE_PATH)
    public static RestMethod createNewTask;

    @ContentType(JSON)
    @DELETE(BASE_PATH + "/{id}")
    public static RestMethod deleteTask;

    @ContentType(JSON)
    @POST(BASE_PATH + "/{id}")
    @QueryParameter(name = "fields", value = "fields(value(name,id),name,$type)")
    @QueryParameter(name = "customFields", value = "State")
    public static RestMethod editStatusTask;

    @ContentType(JSON)
    @POST(BASE_PATH + "/{id}/comments")
    @QueryParameter(name = "fields", value = "text,id")
    public static RestMethod sendCommentToTask;

    @ContentType(JSON)
    @GET(BASE_PATH + "/{id}")
    @QueryParameter(name = "fields", value = "fields(value(name,id),name,$type),comments(text,id)")
    @QueryParameter(name = "customFields", value = "State")
    public static RestMethod getListCommentInTask;

    @ContentType(JSON)
    @POST(BASE_PATH + "/{id}/tags")
    public static RestMethod addTagTask;

    @ContentType(JSON)
    @POST(BASE_PATH + "/{id}")
    public static RestMethod editNameTask;
}
