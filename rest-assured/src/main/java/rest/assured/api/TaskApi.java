package rest.assured.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import rest.assured.dto.taskDto.RequestDtoTask;
import rest.assured.dto.taskDto.ResponseTaskDto;
import rest.assured.dto.commentDto.CommentsDto;
import rest.assured.dto.commentDto.ResponseCommentsDto;
import rest.assured.dto.tagsDto.ResponseTagsDto;
import rest.assured.dto.tagsDto.TagsDto;

import java.util.List;

import static rest.assured.services.JsonFileToString.getRequestBody;

public class TaskApi extends AbsServicesApi {

    private static final String ISSUES_PATH = "/api/issues";

    public List<ResponseTaskDto> getListTask() {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH)
                .queryParam("fields", "$type,id,idReadable,summary,description")
                .when().get()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", ResponseTaskDto.class);
    }

    public ResponseTaskDto createNewTask(RequestDtoTask requestDtoTask) {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH)
                .queryParam("fields", "id,idReadable,summary,description")
                .body(requestDtoTask)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().as(ResponseTaskDto.class);
    }

    public Response editStatusTask(String path, String id) {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH + "/" + id)
                .queryParam("fields", "fields(value(name,id),name,$type)")
                .queryParam("customFields", "State")
                .body(getRequestBody(path))
                .log().all()
                .when().post()
                .then()
                .log().all()
                .extract().response();
    }

    public CommentsDto addCommentInTask(String path, String idTask) {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH + "/" + idTask + "/comments")
                .queryParam("fields", "text,id")
                .body(getRequestBody(path))
                .log().all()
                .when().post()
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(CommentsDto.class);
    }

    public ResponseCommentsDto getListCommentInTask(String idTask) {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH + "/" + idTask)
                .queryParam("fields", "fields(value(name,id),name,$type),comments(text,id)")
                .queryParam("customFields", "State")
                .when().get()
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(ResponseCommentsDto.class);
    }

    public TagsDto addTag(String idTask, String idTag) {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH + "/" + idTask + "/tags")
                .body("{\n" +
                      "  \"$type\": \"Tag\",\n" +
                      "  \"name\": \"new_tag_api\",\n" +
                      "  \"id\": \"" + idTag + "\"" +
                      "}")
                .log().all()
                .when().post()
                .then()
                .statusCode(200)
                .extract().as(TagsDto.class);
    }

    public List<ResponseTagsDto> getListTags(String idTask) {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH + "/" + idTask + "/tags")
                .queryParam("fields", "text,id")
                .when().get()
                .then()
                .extract().jsonPath()
                .getList("", ResponseTagsDto.class);
    }

    public Response editNameTask(String path, String idTask) {
        return RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH + "/" + idTask)
                .body(getRequestBody(path))
                .when().post()
                .then()
                .log().all()
                .extract().response();
    }

    public void deleteTask(String id) {
        RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH)
                .log().all()
                .when().delete(id)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
    }
}
