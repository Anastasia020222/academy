package selenium.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.AbsBasePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static selenium.component.ElementVisibleJS.isElementVisible;

public class ListIssueStatuses extends AbsBasePage<ListIssueStatuses> {

    @FindBy(xpath = "//div[@data-test='ring-popup' and @data-test-shown='true']")
    private WebElement listStatuses;

    public void openListStatus() {
        assertThat("Элемент должен быть отображён", listStatuses.isDisplayed(), is(true));
    }

    public void closeListStatus() {
        assertFalse(isElementVisible(js, listStatuses), "Выпадающий список не закрылся");
    }
}
