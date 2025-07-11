package appium.pages.app;

import appium.data.category.App;
import appium.pages.AbsBasePage;
import appium.pages.app.alertDialogs.AlertDialogs;
import appium.pages.app.menu.MenuPage;
import appium.pages.app.notification.NotificationPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppPages extends AbsBasePage {

    public AppPages(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(id = "android:id/list")
    private WebElement listMainMenu;

    public WebElement getWebElementCategory(String category) {
        return androidDriver.findElement(By.xpath(String.format("//android.widget.TextView[@content-desc='%s']", category)));
    }

    public NotificationPage goNotificationPage() {
        getWebElementCategory(App.NOTIFICATION.getApp()).click();
        return new NotificationPage(androidDriver);
    }

    public AlertDialogs goAlertDialogs() {
        getWebElementCategory(App.ALERT_DIALOGS.getApp()).click();
        return new AlertDialogs(androidDriver);
    }

    public MenuPage goMenuPage() {
        getWebElementCategory(App.MENU.getApp()).click();
        return new MenuPage(androidDriver);
    }
}
