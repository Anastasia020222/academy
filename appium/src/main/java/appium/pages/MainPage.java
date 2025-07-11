package appium.pages;

import appium.data.category.App;
import appium.data.category.Main;
import appium.data.category.Views;
import appium.pages.app.AppPages;
import appium.pages.views.ViewsPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends AbsBasePage {

    public MainPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @FindBy(id = "android:id/content")
    private WebElement contentScreen;

    @FindBy(id = "android:id/list")
    private WebElement listMainMenu;

    public WebElement getWebElementCategory(String category) {
        return androidDriver.findElement(By.xpath(String.format("//android.widget.TextView[@content-desc='%s']", category)));
    }

    public MainPage visibleMainScreen() {
        Assertions.assertTrue(isElementVisible(By.id("android:id/content")), "Главный экран не отобразился. Приложение не загрузилось.");
        return this;
    }

    public MainPage checkListCategoryMainPage() {
        List<String> list = Arrays.stream(Main.values()).map(Main::getCategory).collect(Collectors.toList());
        getListMainMenu(list);
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
        getWebElementCategory(Main.APP.getCategory()).click();
        List<String> list = Arrays.stream(App.values()).map(App::getApp).collect(Collectors.toList());
        getListMainMenu(list);
        return new AppPages(androidDriver);
    }

    public ViewsPage goViewsPage() {
        getWebElementCategory(Main.VIEWS.getCategory()).click();
        List<String> list = Arrays.stream(Views.values()).map(Views::getCategory).collect(Collectors.toList());
        getListMainMenu(list);
        return new ViewsPage(androidDriver);
    }
}