package rest.assured.services.manager;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import rest.assured.api.ServicesApi;
import rest.assured.dto.taskDto.ProjectDto;
import rest.assured.dto.taskDto.RequestDtoTask;
import rest.assured.dto.taskDto.ResponseTaskDto;
import rest.assured.api.TaskApi;

import java.lang.reflect.Field;

import static rest.assured.utils.Constants.DESCRIPTION_TASK;
import static rest.assured.utils.Constants.SUMMARY_TASK;

public class ApiManager implements BeforeEachCallback, AfterEachCallback {

    private static final ThreadLocal<ResponseTaskDto> task = new ThreadLocal<>();

    private final TaskApi taskApi = new TaskApi();

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        ProjectDto projectDto = new ProjectDto("0-582");
        RequestDtoTask requestDtoTask = new RequestDtoTask(projectDto, SUMMARY_TASK, DESCRIPTION_TASK);

        task.set(taskApi.createNewTask(requestDtoTask));

        Object testInstance = extensionContext.getRequiredTestInstance();
        for (Field field : testInstance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ServicesApi.class)) {
                field.setAccessible(true);
                field.set(testInstance, task.get());
            }
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if (task.get() != null) {
            taskApi.deleteTask(task.get().getId());
        }
    }
}
