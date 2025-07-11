package appium.pages.views;

import appium.pages.AbsBasePage;
import appium.pages.views.popUpMenu.PopUpMenu;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewsPage extends AbsBasePage {
    public ViewsPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Popup Menu\"]")
    private WebElement popUpCategory;

    @FindBy(className = "android.widget.Button")
    private WebElement buttonPopUp;

    public PopUpMenu goPopUpMenuPage() {//todo нужно сделать скролл до элемента чтобы он был видим на экране
        popUpCategory.click();
        assertTrue(buttonPopUp.isDisplayed(), "Кнопка popup не отобразилась");
        return new PopUpMenu(androidDriver);
    }
}
