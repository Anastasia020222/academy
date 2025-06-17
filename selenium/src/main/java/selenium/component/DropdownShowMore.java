package selenium.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.AbsBasePage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static selenium.component.ElementVisibleJS.isElementVisible;

public class DropdownShowMore extends AbsBasePage<DropdownShowMore> {

    @FindBy(xpath = "//div[@data-test='ring-popup' and @data-test-shown='true']")
    private WebElement dropdownShowMore;

    public void openDropdownShowMore() {
        assertTrue(isElementVisible(js, dropdownShowMore), "Dropdown не раскрылся");
    }

    public void closeDropdownShowMore() {
        assertFalse(isElementVisible(js, dropdownShowMore), "Dropdown не закрылся");
    }
}
