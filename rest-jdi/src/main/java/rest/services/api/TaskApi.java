package rest.services.api;

import com.epam.http.response.RestResponse;
import org.junit.jupiter.api.Assertions;
import rest.dto.commentDto.CommentsDto;
import rest.dto.commentDto.ResponseCommentsDto;
import rest.dto.errorDto.ErrorResponseDto;
import rest.dto.stateBundleElementDto.ResponseStateElementDto;
import rest.dto.tagsDto.TagsDto;
import rest.dto.taskDto.RequestDtoTask;
import rest.dto.taskDto.ResponseTaskDto;

import java.util.List;

import static com.epam.http.requests.ServiceInit.init;
import static rest.common.Path.*;
import static rest.services.api.APIServiceTask.*;
import static utils.JsonFileToString.getRequestBody;

public class TaskApi {

    public List<ResponseTaskDto> getListTask() {
        RestResponse t = getTaskList.call();
        return t.getRaResponse().then().log().all()
                .extract().body().jsonPath().getList("", ResponseTaskDto.class);
    }

    public ResponseTaskDto createNewTask(RequestDtoTask requestDtoTask) {
        RestResponse response = createNewTask.post(requestDtoTask);

        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");

        ResponseTaskDto responceObj = response.getRaResponse().then().log().all()
                .extract().body().as(ResponseTaskDto.class);
        return responceObj;
    }

    public void deleteTask(String idTask) {
        RestResponse response = deleteTask.pathParams(idTask).call();
        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");
    }

    public ResponseStateElementDto editStatusTask(String idTask) {
        RestResponse response = editStatusTask.pathParams(idTask).post(getRequestBody(REQUEST_STATUS_TASK));

        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");

        return response.getRaResponse().then().log().all().extract().body().as(ResponseStateElementDto.class);
    }

    public CommentsDto sendCommentToTask(String idTask) {
        RestResponse response = sendCommentToTask.pathParams(idTask).post(getRequestBody(REQUEST_COMMENTS_TASK));

        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");

        return response.getRaResponse().then().log().all().extract().body().as(CommentsDto.class);
    }

    public ResponseCommentsDto getListCommentInTask(String idTask) {
        RestResponse response = getListCommentInTask.pathParams(idTask).call();
        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");
        return response.getRaResponse().then().log().all().extract().body().as(ResponseCommentsDto.class);
    }

    public TagsDto addTagTask(String idTask, String tadId) {
        String path = String.format(REQUEST_ADD_TAD, tadId);
        RestResponse response = addTagTask.pathParams(idTask).post(path);
        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");
        return response.getRaResponse().then().log().all().extract().body().as(TagsDto.class);
    }

    public ErrorResponseDto editInvalidStatusTask(String idTask) {
        RestResponse response = editStatusTask.pathParams(idTask).post(getRequestBody(NEGATIVE_REQUEST_STATUS_TASK));

        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(400, code, "Статус код не равен 400");

        return response.getRaResponse().then().log().all().extract().body().as(ErrorResponseDto.class);
    }

    public ErrorResponseDto editNameTaskIsEmpty(String idTask) {
        RestResponse response = editNameTask.pathParams(idTask).post(getRequestBody(NEGATIVE_NAME_TASK));

        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(400, code, "Статус код не равен 400");
        return response.getRaResponse().then().log().all().extract().body().as(ErrorResponseDto.class);
    }
}
