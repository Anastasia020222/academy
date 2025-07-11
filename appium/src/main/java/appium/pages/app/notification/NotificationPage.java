package appium.pages.app.notification;

import appium.pages.AbsBasePage;
import appium.pages.app.notification.statusBar.StatusBar;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class NotificationPage extends AbsBasePage {

    public NotificationPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Status Bar\"]")
    private WebElement statusBarCategory;

    @FindBy(xpath = "//android.widget.ScrollView/android.widget.LinearLayout")
    private WebElement listNotificationStatusBar;

    @FindBy(id = "android:id/list")
    private WebElement listMainMenu;

    public StatusBar goStatusBarPage() {
        statusBarCategory.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.widget.LinearLayout")));
        Assertions.assertTrue(listNotificationStatusBar.isDisplayed(), "Экран status bar не отобразился.");
        return new StatusBar(androidDriver);
    }

    public NotificationPage checkListCategory() {
        List<WebElement> viewList = listMainMenu.findElements(By.id("android:id/text1"));

        viewList.stream()
                .map(WebElement::getText)
                .forEach(nameCategory -> {
                    boolean result = getListCategoryNotification().stream()
                            .anyMatch(e -> e.equals(nameCategory));
                    Assertions.assertTrue(result, "Категория " + nameCategory + " не была найдена среди категорий на главном экране");
                });
        return this;
    }

    private List<String> getListCategoryNotification() {
        return List.of(
                "IncomingMessage",
                "Notifying Service Controller",
                "NotifyWithText",
                "Status Bar"
        );
    }
}
