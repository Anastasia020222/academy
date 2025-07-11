package appium.pages.views;

import appium.data.category.Views;
import appium.pages.AbsBasePage;
import appium.pages.views.popUpMenu.PopUpMenu;
import appium.pages.views.webviews.WebView;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewsPage extends AbsBasePage {

    public ViewsPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(className = "android.widget.Button")
    private WebElement buttonPopUp;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id='android:id/action_bar']//android.widget.TextView")
    private WebElement actionsBarWebView;

    @FindBy(id = "android:id/content")
    private WebElement webViewPage;

    public WebElement getWebElementCategory(String category) {
        return androidDriver.findElement(By.xpath(String.format("//android.widget.TextView[@content-desc='%s']", category)));
    }

    public PopUpMenu goPopUpMenuPage() {
        scrollToElementByDescription("Popup Menu");
        getWebElementCategory(Views.POPUP_MENU.getCategory()).click();
        assertTrue(buttonPopUp.isDisplayed(), "Кнопка popup не отобразилась");
        return new PopUpMenu(androidDriver);
    }

    public WebView goWebView() {
        scrollToElementByDescription("WebView");
        getWebElementCategory(Views.WEB_VIEW.getCategory()).click();
        isElementVisible(By.id("android:id/content"));
        String actualActionsBar = actionsBarWebView.getText();
        assertEquals(actualActionsBar, "Views/WebView", "Путь в шапке экрана не соответствует Views/WebView");
        return new WebView(androidDriver);
    }
}
