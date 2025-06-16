import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rest.assured.api.TaskApi;
import rest.assured.dto.taskDto.ProjectDto;
import rest.assured.dto.taskDto.RequestDtoTask;
import rest.assured.dto.taskDto.ResponseTaskDto;
import rest.assured.services.RequestTaskChecks;

import java.util.List;

import static rest.assured.utils.Constants.DESCRIPTION_TASK;
import static rest.assured.utils.Constants.SUMMARY_TASK;

public class CreateTaskTest {

    private final TaskApi taskApi = new TaskApi();
    private final RequestTaskChecks requestChecks = new RequestTaskChecks();

    @Test
    @DisplayName("Создание задачи")
    void getListTask() {
        ProjectDto projectDto = new ProjectDto("0-582");
        RequestDtoTask requestDtoTask = new RequestDtoTask(projectDto, SUMMARY_TASK, DESCRIPTION_TASK);

        ResponseTaskDto task = taskApi.createNewTask(requestDtoTask);
        List<ResponseTaskDto> getListTask = taskApi.getListTask();

        requestChecks.checkCreateTask(getListTask, requestDtoTask, task);

        taskApi.deleteTask(task.getId());
    }
}
