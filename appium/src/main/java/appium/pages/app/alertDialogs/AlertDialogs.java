package appium.pages.app.alertDialogs;

import appium.pages.AbsBasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlertDialogs extends AbsBasePage {

    public AlertDialogs(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(xpath = "//android.widget.Button[@content-desc='List dialog']")
    private WebElement listDialogs;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id='android:id/parentPanel']")
    private WebElement listDialogOpen;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='android:id/alertTitle']")
    private WebElement titleListDialog;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/contentPanel']")
    private WebElement controlPanelCommandOne;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='android:id/message']")
    private WebElement titleControlPanelCommandOne;

    public WebElement commandOne(String command) {
        return androidDriver.findElement(By.xpath(String.format("//android.widget.TextView[@resource-id='android:id/text1' and @text='%s']", command)));
    }

    public AlertDialogs selectAlertListDialogs() {
        listDialogs.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.LinearLayout[@resource-id='android:id/parentPanel']")));
        assertTrue(listDialogOpen.isDisplayed(), "Диалоговое окно со списком диалогов не раскрылось");

        String title = titleListDialog.getText();
        assertEquals(title, "Header title", "Название диалогового окна не соответствует Header title");
        return this;
    }

    public AlertDialogs selectCommandOne(String command, String text) {
        commandOne(command).click();
        assertTrue(controlPanelCommandOne.isDisplayed(), "Alert после выбора поля Command One не открылся");

        String title = titleControlPanelCommandOne.getText();
        assertEquals(title, title, "Название в окне не соответствует");

        return this;
    }
}
