import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import rest.ApiManager;
import rest.checks.RequestNegativeTaskCheck;
import rest.dto.errorDto.ErrorResponseDto;
import rest.dto.taskDto.ResponseTaskDto;
import rest.services.ServicesApi;
import rest.services.api.TaskApi;

@ExtendWith(ApiManager.class)
public class NegativeTaskTest {

    private final TaskApi taskApi = new TaskApi();
    private final RequestNegativeTaskCheck requestTask = new RequestNegativeTaskCheck();

    @ServicesApi
    private ResponseTaskDto task;

    @Test
    @DisplayName("Изменить статус задачи на несуществующий")
    void editInvalidStatusTask() {
        ErrorResponseDto response = taskApi
                .editInvalidStatusTask(task.getId());
        requestTask
                .editInvalidStatusTask(response);
    }

    @Test
    @DisplayName("Изменить название задачи на пустое")
    void editNameTaskEmpty() {
        ErrorResponseDto responseDto = taskApi
                .editNameTaskIsEmpty(task.getId());
        requestTask
                .editNameTaskEmpty(responseDto);
    }
}
