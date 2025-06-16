import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import rest.ApiManager;
import rest.checks.RequestTagsChecks;
import rest.checks.RequestTaskChecks;
import rest.dto.commentDto.CommentsDto;
import rest.dto.commentDto.ResponseCommentsDto;
import rest.dto.stateBundleElementDto.ResponseStateElementDto;
import rest.dto.tagsDto.ResponseTagsDto;
import rest.dto.tagsDto.TagsDto;
import rest.dto.taskDto.ResponseTaskDto;
import rest.services.ServicesApi;
import rest.services.api.TagsApi;
import rest.services.api.TaskApi;

import java.util.List;

import static rest.common.Path.REQUEST_TAGS;

@ExtendWith(ApiManager.class)
public class TaskApiTest{

    private final TaskApi taskApi = new TaskApi();
    private final TagsApi tagsApi = new TagsApi();
    private final RequestTaskChecks requestChecks = new RequestTaskChecks();
    private final RequestTagsChecks requestTagsChecks = new RequestTagsChecks();

    @ServicesApi
    private ResponseTaskDto task;

    @Test
    @DisplayName("Изменение статуса задачи")
    void editStatusTask() {
        ResponseStateElementDto responseStateElementDto = taskApi.editStatusTask(task.getId());
        requestChecks
                .checkStatusTask(responseStateElementDto);
    }

    @Test
    @DisplayName("Отправить комментарий в задачу")
    void sendCommentToTask() {
        System.out.println("task sendCommentToTask" + task.getId());
        CommentsDto commentsDto = taskApi.sendCommentToTask(task.getId());
        ResponseCommentsDto responseCommentsDto = taskApi.getListCommentInTask(task.getId());
        requestChecks.checkAddCommentTask(commentsDto, responseCommentsDto);
    }

    @Test
    @DisplayName("Добавить тег в задачу")
    void addTagInTask() {
        TaskApi taskApi = new TaskApi();
        System.out.println("task addTagInTask " + task.getId());
        TagsDto createTag = tagsApi.createTags(REQUEST_TAGS);
        TagsDto addTask = taskApi.addTagTask(task.getId(), createTag.getId());

        List<ResponseTagsDto> list = tagsApi.getListTagsInTask(task.getId());
        requestTagsChecks.checkTagsInTask(addTask, list);
        tagsApi.deleteTag(createTag.getId());
    }
}
