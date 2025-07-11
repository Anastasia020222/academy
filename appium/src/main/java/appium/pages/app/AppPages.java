package appium.pages.app;

import appium.pages.AbsBasePage;
import appium.pages.app.alertDialogs.AlertDialogs;
import appium.pages.app.menu.MenuPage;
import appium.pages.app.notification.NotificationPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppPages extends AbsBasePage {

    public AppPages(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Notification\"]")
    private WebElement notificationPage;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Alert Dialogs\"]")
    private WebElement alertDialogs;

    @FindBy(id = "android:id/list")
    private WebElement listMainMenu;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Menu\"]")
    private WebElement menuPage;

    public NotificationPage goNotificationPage() {
        notificationPage.click();
        return new NotificationPage(androidDriver);
    }

    public AlertDialogs goAlertDialogs() {
        alertDialogs.click();

        return new AlertDialogs(androidDriver);
    }

    public MenuPage goMenuPage() {
        menuPage.click();
        return new MenuPage(androidDriver);
    }
}
