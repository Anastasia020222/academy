package selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WebDriverFactory {

    private static final String url = System.getProperty("base.url");

    public static WebDriver createWebDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(getOptions());
    }

    private static ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-proxy-server");
        options.addArguments("start-maximized");
        return options;
    }

    public static WebDriver getWebDriver() {
        WebDriver driver = createWebDriver();
        driver.get(url);

        String tokenJson = readTokenJson();
        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.setItem(arguments[0], arguments[1]);",
                "cf6f74d5-c1b8-457f-9d4b-2348fe19440f-token", tokenJson
        );

        driver.navigate().refresh();
        return driver;
    }

    private static String readTokenJson() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/token.json")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read token.json", e);
        }
    }
}
