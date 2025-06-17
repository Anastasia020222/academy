package selenium.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.AbsBasePage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static selenium.component.ElementVisibleJS.isElementVisible;

public class DialogDeleteTask extends AbsBasePage<DialogDeleteTask> {

    @FindBy(xpath = "//div[@data-test='ring-dialog-container ring-confirm']//div[@data-test='ring-island ring-dialog']")
    private WebElement dialogDeleteTask;

    public void openDialogDeleteTask() {
        assertTrue(isElementVisible(js, dialogDeleteTask), "Диалоговое окно подтверждения удаления не открылось");

    }

    public void closeDialogDeleteTask() {
        assertFalse(isElementVisible(js, dialogDeleteTask), "Диалоговое окно подтверждения удаления не закрылось");
    }
}
