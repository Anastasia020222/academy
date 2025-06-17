package selenium.driver;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import selenium.api.ApiRequest;
import selenium.api.annotations.CreateTask;
import selenium.api.annotations.DeleteTask;

import static selenium.driver.WebDriverFactory.getWebDriver;

public class DriverManager implements BeforeEachCallback, AfterEachCallback {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<ApiRequest> request = new ThreadLocal<>();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        if (driver.get() == null) {
            driver.set(getWebDriver());
        }
        createApi(context);
        if (createTask(context)) {
            request.get().createTask();
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (driver.get() != null) {
            if (driver.get() != null) {
                driver.get().quit();
                driver.remove();
            }
            createApi(context);
            if (deleteTask(context)) {
                request.get().deleteTask();
                request.remove();
            }
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    private boolean createTask(ExtensionContext context) throws Exception {
        CreateTask createTask = context.getRequiredTestMethod().getAnnotation(CreateTask.class);
        return createTask != null;
    }

    private boolean deleteTask(ExtensionContext context) throws Exception {
        DeleteTask deleteTask = context.getRequiredTestMethod().getAnnotation(DeleteTask.class);
        return deleteTask != null;
    }

    private void createApi(ExtensionContext context) throws Exception {
        if ((createTask(context) || deleteTask(context)) && request.get() == null) {
            request.set(new ApiRequest());
        }
    }
}
