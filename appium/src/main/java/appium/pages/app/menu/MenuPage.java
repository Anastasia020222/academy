package appium.pages.app.menu;

import appium.Driver;
import appium.pages.AbsBasePage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

import static appium.utils.Constants.TEXT_MENU_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuPage extends AbsBasePage {
    public MenuPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Inflate from XML\"]")
    private WebElement inflateFromXML;

    @FindBy(xpath = "//android.widget.TextView[@text=\"If you want to choose another menu resource, go back and re-run this activity.\"]")
    private WebElement textMenuPage;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\"]")
    private WebElement selectField;

    @FindBy(xpath = "//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Visible\"]")
    private WebElement option;

    @FindBy(xpath = "//android.widget.ListView")
    private WebElement listOptions;

    public MenuPage goInflateFromXML() {
        inflateFromXML.click();
        assertTrue(selectField.isDisplayed(), "Поле выпадающего списка не отобразилось");
        String actualSelectText = selectField.getText();
        System.out.println("до " + actualSelectText);
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
        System.out.println("после " + actualSelectText);
        assertEquals(actualSelectText, "Visible", "В поле выпадающего меню опция не изменилась на Visible");
        return this;
    }
}
