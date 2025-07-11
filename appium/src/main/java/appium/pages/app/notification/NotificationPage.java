package appium.pages.app.notification;

import appium.data.category.Notification;
import appium.pages.AbsBasePage;
import appium.pages.app.notification.statusBar.StatusBar;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationPage extends AbsBasePage {

    public NotificationPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(xpath = "//android.widget.ScrollView/android.widget.LinearLayout")
    private WebElement listNotificationStatusBar;

    @FindBy(id = "android:id/list")
    private WebElement listMainMenu;

    public WebElement getWebElementCategory(String category) {
        return androidDriver.findElement(By.xpath(String.format("//android.widget.TextView[@content-desc='%s']", category)));
    }

    public StatusBar goStatusBarPage() {
        getWebElementCategory(Notification.STATUS_BAR.getCategory()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.widget.LinearLayout")));
        Assertions.assertTrue(listNotificationStatusBar.isDisplayed(), "Экран status bar не отобразился.");
        return new StatusBar(androidDriver);
    }

    public NotificationPage checkListCategory() {
        List<String> list = Arrays.stream(Notification.values()).map(Notification::getCategory).collect(Collectors.toList());
        getListCategory(list);
        return this;
    }

    public NotificationPage getListCategory(List<String> list) {
        List<WebElement> viewList = listMainMenu.findElements(By.id("android:id/text1"));

        viewList.stream()
                .map(WebElement::getText)
                .forEach(nameCategory -> {
                    boolean result = list.stream()
                            .anyMatch(e -> e.equals(nameCategory));
                    Assertions.assertTrue(result, "Категория " + nameCategory + " не была найдена среди категорий на главном экране");
                });
        return this;
    }
}
