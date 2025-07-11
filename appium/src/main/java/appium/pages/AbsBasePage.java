package appium.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbsBasePage {

    protected AndroidDriver androidDriver;
    protected WebDriverWait wait;

    public AbsBasePage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
        this.wait = new WebDriverWait(androidDriver, Duration.ofSeconds(15));
        PageFactory.initElements(this.androidDriver, this);
    }

    public boolean isElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
