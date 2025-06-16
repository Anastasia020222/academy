import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import rest.ApiManager;
import rest.checks.RequestTaskChecks;
import rest.dto.taskDto.ProjectDto;
import rest.dto.taskDto.RequestDtoTask;
import rest.dto.taskDto.ResponseTaskDto;
import org.junit.jupiter.api.Test;
import rest.services.api.TaskApi;

import java.util.List;

import static rest.common.Constants.DESCRIPTION_TASK;
import static rest.common.Constants.SUMMARY_TASK;

@ExtendWith(ApiManager.class)
public class CreateTaskApiTest {

    private final TaskApi taskApi = new TaskApi();
    private final RequestTaskChecks requestChecks = new RequestTaskChecks();

    @Test
    @DisplayName("Создание задачи")
    void createTask() {
        ProjectDto projectDto = new ProjectDto("0-582");
        RequestDtoTask requestDtoTask = new RequestDtoTask(projectDto, SUMMARY_TASK, DESCRIPTION_TASK);
        ResponseTaskDto create = taskApi.createNewTask(requestDtoTask);

        List<ResponseTaskDto> getListTask = taskApi.getListTask();

        requestChecks.checkCreateTask(getListTask, requestDtoTask, create);

        taskApi.deleteTask(create.getId());
    }
}
