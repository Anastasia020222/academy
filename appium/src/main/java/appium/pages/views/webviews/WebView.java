package appium.pages.views.webviews;

import appium.pages.AbsBasePage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static appium.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebView extends AbsBasePage {

    public WebView(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(xpath = "//android.webkit.WebView[@index=0]/android.widget.TextView[@index = 0]")
    private WebElement title;

    @FindBy(xpath = "//android.webkit.WebView[@index=0]/android.widget.TextView[@index = 1]")
    private WebElement description;

    @FindBy(xpath = "//android.view.View[@resource-id='i am a link' and @index = 2]/android.widget.TextView[@index=0]")
    private WebElement link;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='i_am_a_textbox']")
    private WebElement fieldTextBox;

    public WebView checkTitle() {
        String actualTitle = title.getText();
        assertEquals(actualTitle, TITLE_WEB_VIEW, "Title на экране WebView отобразился неверный");
        return this;
    }

    public WebView checkDescription() {
        String actualDescription = description.getText();
        assertEquals(actualDescription, DESCRIPTION_WEB_VIEW, "Описание на экране WebView отобразилось неверное");
        String actualLink = link.getText();
        assertEquals(actualLink, LINK_WEB_VIEW, "Текст ссылки на экране WebView отобразилось неверное");
        return this;
    }

    public WebView checkField() {
        assertTrue(fieldTextBox.isDisplayed(), "После ввода не отобразилось");
        String titleField = fieldTextBox.getText();
        Assertions.assertEquals(titleField, TEXT_FIELD_WEB_VIEW, "Текст в поле ввода отобразилось неверное");
        return this;
    }

    public WebView focusedField() {
        fieldTextBox.click();
        String isFocused = fieldTextBox.getAttribute("focused");
        assertEquals(isFocused, "true", "Поле должно быть в фокусе");
        return this;
    }
}
