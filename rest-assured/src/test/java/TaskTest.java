import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import rest.assured.api.ServicesApi;
import rest.assured.dto.taskDto.ResponseTaskDto;
import rest.assured.dto.commentDto.CommentsDto;
import rest.assured.dto.commentDto.ResponseCommentsDto;
import rest.assured.dto.stateBundleElementDto.ResponseStateElementDto;
import rest.assured.dto.tagsDto.ResponseTagsDto;
import rest.assured.dto.tagsDto.TagsDto;
import rest.assured.api.TagsApi;
import rest.assured.api.TaskApi;
import rest.assured.services.RequestTaskChecks;
import rest.assured.services.manager.ApiManager;

import java.util.List;

import static rest.assured.utils.Path.*;

@ExtendWith(ApiManager.class)
public class TaskTest {

    private final TaskApi taskApi = new TaskApi();
    private final TagsApi tagsApi = new TagsApi();
    private final RequestTaskChecks requestChecks = new RequestTaskChecks();

    @ServicesApi
    private ResponseTaskDto task;

    @Test
    @DisplayName("Изменение статуса задачи")
    void editStatusTask() {
        Response response = taskApi
                .editStatusTask(REQUEST_STATUS_TASK, task.getId());
        ResponseStateElementDto list = response
                .body().as(ResponseStateElementDto.class);
        requestChecks
                .checkStatusTask(list);
    }

    @Test
    @DisplayName("Отправить комментарий в задачу")
    void sendCommentToTask() {
        CommentsDto commentsDto = taskApi
                .addCommentInTask(REQUEST_COMMENTS_TASK, task.getId());
        ResponseCommentsDto list = taskApi
                .getListCommentInTask(task.getId());
        requestChecks
                .checkAddCommentTask(commentsDto, list);
    }

    @Test
    @DisplayName("Добавить тег в задачу")
    void addTagInTask() {
        Response response = tagsApi
                .createTag(REQUEST_TAGS);

        int code = response.statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");

        TagsDto createTag = response.body().as(TagsDto.class);
        TagsDto tagsDto = taskApi.addTag(task.getId(), createTag.getId());
        List<ResponseTagsDto> list = taskApi.getListTags(task.getId());
        requestChecks
                .checkTagsInTask(tagsDto, list);
        tagsApi
                .deleteTag(createTag.getId());
    }
}
