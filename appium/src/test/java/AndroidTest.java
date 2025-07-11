import appium.manager.Driver;
import appium.extension.AndroidExtension;
import appium.pages.MainPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@ExtendWith(AndroidExtension.class)
public class AndroidTest {

    @Driver
    private AndroidDriver androidDriver;

    @Test
    @DisplayName("Запуск приложения. Отображение главного экрана")
    void testMainScreen() {
        new MainPage(androidDriver)
                .visibleMainScreen()
                .checkListCategoryMainPage();
    }

    @Test
    @DisplayName("Взаимодействие с WebView. Отображение WebView")
    void testWebView() {
        new MainPage(androidDriver)
                .goViewsPage()
                .goWebView()
                .checkTitle()
                .checkDescription()
                .checkField()
                .focusedField();
    }

    @Test
    @DisplayName("Выбор категории 'Add' в Popup Menu")
    void testCategoryNavigation() {
        new MainPage(androidDriver)
                .goViewsPage()
                .goPopUpMenuPage()
                .clickButtonPopUP()
                .selectOptionAdd();
    }

    @ParameterizedTest(name = "Нотификации из списка Icons Only: {0}")
    @MethodSource("appium.data.StatusBarData#statusBarNoty")
    void testNotifications(String type, String title, String description) {
        new MainPage(androidDriver)
                .goAppPage()
                .goNotificationPage()
                .checkListCategory()
                .goStatusBarPage()
                .permissionNotify()
                .checkVisibleIconsOnly(type, title)
                .checkNotificationHappy(type, title, description);
    }

    @ParameterizedTest(name = "Диалоговые окна List Dialog: {0}")
    @MethodSource("appium.data.StatusBarData#listDialogs")
    void testAlertDialogs(String command, String text) {
        new MainPage(androidDriver)
                .goAppPage()
                .goAlertDialogs()
                .selectAlertListDialogs()
                .selectCommandOne(command, text);
    }

    @Test
    @DisplayName("Выпадающее меню. Выбор варианта Visible")
    void testContextMenu() {
        new MainPage(androidDriver)
                .goAppPage()
                .goMenuPage()
                .goInflateFromXML()
                .selectOptionMenu()
                .checkSelectedOptionsVisible();
    }
}
