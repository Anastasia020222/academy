package selenium.component;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ElementVisibleJS {

//    public static boolean isElementVisible(JavascriptExecutor js, WebElement element) {
//        return (Boolean) js.executeScript(
//                "return arguments[0].offsetParent !== null && " +
//                "window.getComputedStyle(arguments[0]).visibility !== 'hidden' && " +
//                "parseFloat(window.getComputedStyle(arguments[0]).opacity) > 0;",
//                element
//        );
//    }

    public static boolean isElementVisible(JavascriptExecutor js, WebElement element) {
        try {
            Boolean isVisible = (Boolean) js.executeScript(
                    "return arguments[0].offsetParent !== null && " +
                    "window.getComputedStyle(arguments[0]).visibility !== 'hidden' && " +
                    "parseFloat(window.getComputedStyle(arguments[0]).opacity) > 0;",
                    element
            );
            return isVisible != null && isVisible;
        } catch (Exception e) {
            return false;
        }
    }

}
