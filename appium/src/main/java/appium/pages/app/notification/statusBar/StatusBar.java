package appium.pages.app.notification.statusBar;

import appium.pages.AbsBasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatusBar extends AbsBasePage {

    public StatusBar(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Icons only']")
    private WebElement iconsOnly;

    @FindBy(xpath = "(//android.widget.FrameLayout[@resource-id='android:id/status_bar_latest_event_content'])[1]")
    private WebElement statusBarEvent;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.android.permissioncontroller:id/grant_dialog']")
    private WebElement dialogWindow;

    @FindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']")
    private WebElement buttonAllowDialog;

    @FindBy(xpath = "//android.widget.Button[@resource-id='io.appium.android.apis:id/happy']")
    private WebElement iconsOnlyHappy;

    public WebElement iconsOnly(String type) {
        return androidDriver.findElement(By.xpath(String.format("//android.widget.Button[@resource-id='io.appium.android.apis:id/%s']", type)));
    }

    public StatusBar checkVisibleIconsOnly(String type, String title) {
        String nameIconsOnly = iconsOnly.getText();
        assertEquals(nameIconsOnly, "Icons only", "Тип нотификации Icons only не отобразился");
        assertTrue(iconsOnly(type).isDisplayed(), "Иконка " + title + " в типе нотификации Icons only не отобразилась");
        return this;
    }

    public StatusBar checkNotificationHappy(String type, String title, String description) {
        iconsOnly(type).click();

        androidDriver.openNotifications();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.FrameLayout[@resource-id='com.android.systemui:id/expandableNotificationRow'])[1]")));

        String nameEvent = statusBarEvent.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/app_name_text' and @text='API Demos']")).getText();
        String titleEvent = statusBarEvent.findElement(By.xpath(String.format("//android.widget.TextView[@resource-id='android:id/title' and @text='%s']", title))).getText();
        String descriptionEvent = statusBarEvent.findElement(By.xpath(String.format("//android.widget.TextView[@resource-id='android:id/text' and @text='%s']", description))).getText();

        assertEquals(nameEvent, "API Demos", "Название приложения на уведомлении не соответствует API Demos");
        assertEquals(titleEvent, title, "Название уведомления не соответствует " + title);
        assertEquals(descriptionEvent, description, "Описание уведомления не соответствует " + description);

        androidDriver.navigate().back();
        return this;
    }

    public StatusBar permissionNotify() {
        assertTrue(dialogWindow.isDisplayed(), "Диалоговое окно не открылось");
        buttonAllowDialog.click();
        return this;
    }
}
