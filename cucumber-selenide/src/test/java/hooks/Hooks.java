package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import cucumber.selenide.api.ApiRequest;
import cucumber.selenide.utils.AuthHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

    private final AuthHelper authHelper = new AuthHelper();
    ApiRequest apiRequest = new ApiRequest();

    @Before
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-proxy-server");
        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.screenshots = true;
        Configuration.baseUrl = "http://добавить домен проекта";
        authHelper.loginWithLocalStorageToken();
    }

    @After
    public void close() {
        if (apiRequest.getTask() != null) {
            apiRequest.deleteTask();
        }
        Selenide.closeWebDriver();
    }
}
