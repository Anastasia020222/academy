package appium.pages;

import appium.pages.app.AppPages;
import appium.pages.views.ViewsPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends AbsBasePage {

    public MainPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(id = "android:id/content")
    private WebElement contentScreen;

    @FindBy(id = "android:id/list")
    private WebElement listMainMenu;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"App\"]")
    private WebElement appCategory;

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Views\"]")
    private WebElement viewsCategory;

    public MainPage visibleMainScreen() {
        Assertions.assertTrue(isElementVisible(By.id("android:id/content")), "Главный экран не отобразился. Приложение не загрузилось.");
        return this;
    }

    public MainPage checkListCategoryMainPage() {
        getListMainMenu(listCategoryMainPage());
        return this;
    }

    public void getListMainMenu(List<String> listCategory) {
        List<WebElement> viewList = listMainMenu.findElements(By.id("android:id/text1"));

        viewList.stream()
                .map(WebElement::getText)
                .forEach(nameCategory -> {
                    boolean result = listCategory.stream()
                            .anyMatch(e -> e.equals(nameCategory));
                    Assertions.assertTrue(result, "Категория " + nameCategory + " не была найдена среди категорий на главном экране");
                });
    }

    public AppPages goAppPage() {
        appCategory.click();
        getListMainMenu(listCategoryAppPage());
        return new AppPages(androidDriver);
    }

    public ViewsPage goViewsPage() {
        viewsCategory.click();
        return new ViewsPage(androidDriver);
    }

    private List<String> listCategoryAppPage() {
        return List.of(
                "Action Bar",
                "Activity",
                "Alarm",
                "Alert Dialogs",
                "Device Admin",
                "Fragment",
                "Launcher Shortcuts",
                "Loader",
                "Menu",
                "Notification",
                "Search",
                "Service",
                "Text-To-Speech",
                "Voice Recognition"
        );
    }

    private List<String> listCategoryMainPage() {
        return List.of(
                "Access'ibility",
                "Accessibility",
                "Animation",
                "App",
                "Content",
                "Graphics",
                "Media",
                "NFC",
                "OS",
                "Preference",
                "Text",
                "Views"
        );
    }
}