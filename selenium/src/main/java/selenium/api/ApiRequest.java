package selenium.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import selenium.task.Task;

import static selenium.api.JsonFileToString.getRequestBody;

@Getter
public class ApiRequest extends AbsRequestSpec {

    private final String url = System.getProperty("base.url");
    private static final String ISSUES_PATH = "/api/issues";

    private final String token = "perm-0JDQvdCw0YHRgtCw0YHQuNGPX9Cb0YvRgdC10L3QutC+.NDQtMTQ=.ZG0Qk5IZy2cYJB024uAgj0kdF52IeR";
    public static final ThreadLocal<Task> task = new ThreadLocal<>();

    public void createTask() {
        Response response = RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH)
                .queryParam("fields", "id,idReadable,summary,description")
                .body(getRequestBody())
                .when()
                .post()
                .then().log().all()
                .extract().response();

        int code = response.statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");

        JsonPath jsonPath = response.jsonPath();
        String id = jsonPath.getString("idReadable");
        String summary = jsonPath.getString("summary");

        Task newTask = new Task(id, summary);
        task.set(newTask);
    }

    public void deleteTask() {
        Response response = RestAssured
                .given()
                .spec(spec)
                .basePath(ISSUES_PATH)
                .log().all()
                .when()
                .delete(task.get().getId())
                .then().log().all()
                .extract().response();

        int code = response.statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");
    }

    public static Task getTask() {
        if (task.get() == null) {
            Task newTask = new Task("0", "");
            task.set(newTask);
            return newTask;
        } else {
            return task.get();
        }
    }
}
