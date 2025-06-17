package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.api.ApiRequest;
import selenium.driver.DriverManager;
import selenium.task.Task;

import java.time.Duration;

public abstract class AbsBasePage<T> {

    protected WebDriver driver;
    protected Task task;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    private final String url = System.getProperty("base.url");

    public AbsBasePage() {
        this.driver = DriverManager.getDriver();
        this.task = ApiRequest.task.get();
        this.js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public T open(String path) {
        driver.get(url + path);
        return (T) this;
    }

    protected void customWaitVisibility(By locator, String namePage) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new RuntimeException("Страница " + namePage + " не загрузилась");
        }
    }
}
