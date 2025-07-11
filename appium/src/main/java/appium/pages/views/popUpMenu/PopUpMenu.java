package appium.pages.views.popUpMenu;

import appium.pages.AbsBasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static appium.utils.Constants.CLICK_ITEM_ADD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PopUpMenu extends AbsBasePage {
    public PopUpMenu(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(className = "android.widget.Button")
    private WebElement buttonPopUp;

    @FindBy(xpath = "//android.widget.ListView")
    private WebElement openPopUp;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title' and @text='Add']")
    private WebElement optionAdd;

    @FindBy(xpath = "//android.widget.Toast[@text='Clicked popup menu item Add']")
    private WebElement messageClickedAdd;

    public PopUpMenu clickButtonPopUP() {
        buttonPopUp.click();
        isElementVisible(By.xpath("//android.widget.ListView"));
        assertTrue(openPopUp.isDisplayed(), "Поп-ап не раскрылся");
        return this;
    }

    public PopUpMenu selectOptionAdd() {
        optionAdd.click();
        String message = messageClickedAdd.getText();
        assertEquals(message, CLICK_ITEM_ADD, "Текст в всплывающем оповещении не появился или текст не соответствует");
        return this;
    }
}
