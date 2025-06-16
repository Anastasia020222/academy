package rest;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import rest.dto.taskDto.ProjectDto;
import rest.dto.taskDto.RequestDtoTask;
import rest.dto.taskDto.ResponseTaskDto;
import rest.services.ServicesApi;
import rest.services.api.APIServiceTags;
import rest.services.api.APIServiceTask;
import rest.services.api.TaskApi;

import java.lang.reflect.Field;

import static com.epam.http.requests.ServiceInit.init;
import static rest.common.Constants.DESCRIPTION_TASK;
import static rest.common.Constants.SUMMARY_TASK;

public class ApiManager implements BeforeEachCallback, AfterEachCallback {

    private static final ThreadLocal<ResponseTaskDto> task = new ThreadLocal<>();
    private final TaskApi taskApi = new TaskApi();

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        init(APIServiceTask.class);
        init(APIServiceTags.class);

        ProjectDto projectDto = new ProjectDto("0-582");
        RequestDtoTask requestDtoTask = new RequestDtoTask(projectDto, SUMMARY_TASK, DESCRIPTION_TASK);

        Object testInstance = extensionContext.getRequiredTestInstance();
        for (Field field : testInstance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ServicesApi.class)) {
                field.setAccessible(true);
                task.set(taskApi.createNewTask(requestDtoTask));
                field.set(testInstance, task.get());
            }
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if (task.get() != null) {
            taskApi.deleteTask(task.get().getId());
            task.remove();
        }
    }
}
