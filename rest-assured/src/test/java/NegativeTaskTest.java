import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import rest.assured.api.ServicesApi;
import rest.assured.dto.taskDto.ResponseTaskDto;
import rest.assured.api.TaskApi;
import rest.assured.services.RequestNegativeTaskCheck;
import rest.assured.services.manager.ApiManager;

import static rest.assured.utils.Path.*;

@ExtendWith(ApiManager.class)
public class NegativeTaskTest {

    private final TaskApi taskApi = new TaskApi();
    private final RequestNegativeTaskCheck requestTask = new RequestNegativeTaskCheck();

    @ServicesApi
    private ResponseTaskDto task;

    @Test
    @DisplayName("Изменить статус задачи на несуществующий")
    void editInvalidStatusTask() {
        Response response = taskApi
                .editStatusTask(NEGATIVE_REQUEST_STATUS_TASK, task.getId());
        requestTask
                .editInvalidStatusTask(response);
    }

    @Test
    @DisplayName("Изменить название задачи на пустое")
    void editNameTaskEmpty() {
        Response response = taskApi
                .editNameTask(NEGATIVE_NAME_TASK, task.getId());
        requestTask
                .editNameTaskEmpty(response);
    }
}
