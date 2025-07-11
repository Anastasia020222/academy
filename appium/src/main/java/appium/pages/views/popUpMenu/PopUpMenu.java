package appium.pages.views.popUpMenu;

import appium.pages.AbsBasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Add\"]")
    private WebElement optionAdd;

    @FindBy(xpath = "//android.widget.Toast[@text=\"Clicked popup menu item Add\"]")
    private WebElement messageClickedAdd;

    public PopUpMenu clickButtonPopUP() {
        buttonPopUp.click();
        assertTrue(openPopUp.isDisplayed(), "Поп-ап не раскрылся");
        return this;
    }

    public PopUpMenu selectOptionAdd() {
        optionAdd.click();
        String message = messageClickedAdd.getText();
        assertEquals(message, "Clicked popup menu item Add", "Текст в всплывающем оповещении не появился или текст не соответствует");
        return this;
    }
}
