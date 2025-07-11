package appium.pages.app.menu;

import appium.pages.AbsBasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static appium.utils.Constants.TEXT_MENU_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuPage extends AbsBasePage {
    public MenuPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(xpath = "//android.widget.ListView[@resource-id='android:id/list']//android.widget.TextView[@resource-id='android:id/text1']")
    private WebElement inflateFromXML;

    @FindBy(xpath = "//android.widget.LinearLayout[@index=0]//android.widget.TextView[@index=1]")
    private WebElement textMenuPage;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1']")
    private WebElement selectField;

    @FindBy(xpath = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Visible']")
    private WebElement option;

    @FindBy(xpath = "//android.widget.ListView")
    private WebElement listOptions;

    public MenuPage goInflateFromXML() {
        inflateFromXML.click();
        assertTrue(selectField.isDisplayed(), "Поле выпадающего списка не отобразилось");
        String actualSelectText = selectField.getText();
        assertEquals(actualSelectText, "Title only", "По умолчанию в поле выпадающего меню указан не тот текст");
        String text = textMenuPage.getText();
        assertEquals(text, TEXT_MENU_PAGE, "Текст на странице выпадающего меню неверный");
        return this;
    }

    public MenuPage selectOptionMenu() {
        selectField.click();
        assertTrue(listOptions.isDisplayed(), "Выпадающий список не раскрылся");
        option.click();
        return this;
    }

    public MenuPage checkSelectedOptionsVisible() {
        String actualSelectText = selectField.getText();
        assertEquals(actualSelectText, "Visible", "В поле выпадающего меню опция не изменилась на Visible");
        return this;
    }
}
