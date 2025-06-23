package cucumber.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static cucumber.selenide.utils.Constants.NEW_NAME_TICKET;

public class IssuesPage extends AbsBasePage {

    SelenideElement taskIdLink = $x(String.format("//a[@data-test='ring-link ticket-id'][text()='%s']", task.getId()));
    SelenideElement containerTask = $x("//div[@data-test=\"issue-container\"]");
    SelenideElement editTitleTask = $x("//span[@data-test=\"ring-tooltip\"]//button[@data-test=\"edit-issue-button\"]");
    SelenideElement buttonSaveTask = $("[data-test='save-button']");
    SelenideElement titleTask = $x("//h1[@data-test=\"ticket-summary\"]");
    SelenideElement activeFieldEditTitle = $x("//textarea[@data-test=\"summary\"]");
    SelenideElement dropdownListStatuses = $x("//div[@data-test='ring-popup' and @data-test-shown='true']");
    SelenideElement selectStatus = $x("//div[@data-test='field' and @aria-label='Состояние']//span[@data-test='ring-tooltip field-value']");
    SelenideElement descriptionTask = $x("//div[@data-test=\"wysiwyg-editor-content\"]");

    public SelenideElement getFieldLabelStatus(String label) {
        return $x(String.format("//div[@data-test='field']//label[@data-test='field-label'][text()='%s']", label));
    }

    public SelenideElement getFieldStatus(String name) {
        return $x(String.format("//span[@data-test='ring-list-item-label' and text()='%s']", name));
    }

    public IssuesPage clickLinkTicketId() {
        taskIdLink.shouldBe(visible.because("Задача с id " + task.getId() + " не отобразилась в списке задач"));
        taskIdLink.click();
        containerTask.shouldBe(visible.because("Страница задачи не открылась"));
        return this;
    }

    public IssuesPage selectOptionEditTask() {
        editTitleTask.click();
        activeFieldEditTitle.shouldBe(visible.because("Поле для ввода теста не стало активным"));
        activeFieldEditTitle.click();

        String borderColor = activeFieldEditTitle.getCssValue("border-bottom-color");

        Assertions.assertEquals(borderColor, "rgba(53, 116, 240, 1)", "Поле не подчеркнуто цветом rgba(53, 116, 240, 1)");
        return this;
    }

    public IssuesPage inputNewTitleTask(String new_title) {
        activeFieldEditTitle.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        activeFieldEditTitle.sendKeys(Keys.BACK_SPACE);
        String titleEmpty = activeFieldEditTitle.getAttribute("textContent");
        Assertions.assertEquals(titleEmpty, "", "Поле ввода не стало пустым");

        activeFieldEditTitle.sendKeys(new_title);
        String title = activeFieldEditTitle.getAttribute("textContent");
        Assertions.assertEquals(title, new_title, "В поле не введено новое название");
        return this;
    }

    public IssuesPage inputNewDescriptionTask(String description) {
        descriptionTask.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        descriptionTask.sendKeys(Keys.BACK_SPACE);
        String titleEmpty = descriptionTask.getAttribute("textContent");
        Assertions.assertEquals(titleEmpty, "", "Поле ввода не стало пустым");

        descriptionTask.sendKeys(description);
        String desc = descriptionTask.getAttribute("textContent");
        Assertions.assertEquals(desc, description, "В поле описания не введено новое значение");
        return this;
    }

    public IssuesPage clickButtonSave() {
        buttonSaveTask.click();
        activeFieldEditTitle.shouldNotBe(visible.because("Поле для ввода теста всё ещё активно"));
        buttonSaveTask.shouldNotBe(visible.because("Кнопка Сохранить не исчезла"));
        return this;
    }

    public IssuesPage checkSaveTitleTask() {
        String summary = titleTask.getAttribute("textContent");
        Assertions.assertEquals(summary, NEW_NAME_TICKET, "Название задачи не изменилось");
        return this;
    }

    public IssuesPage clickFieldLabelStatus(String field) {
        SelenideElement labelField = getFieldLabelStatus(field);
        labelField.click();
        dropdownListStatuses.shouldBe(visible.because("Выпадающий список не открылся"));
        return this;
    }

    public IssuesPage selectStatus(String status) {
        SelenideElement fieldStatus = getFieldStatus(status);
        fieldStatus.shouldBe(visible.because("Статус " + status + " не отобразился"));
        fieldStatus.click();
        dropdownListStatuses.shouldNotBe(visible.because("Выпадающий список не закрылся"));
        return this;
    }

    public IssuesPage checkStatusTask(String status) {
        selectStatus.shouldBe(visible.because("Статус не отобразился"));
        String newStatus = selectStatus.getAttribute("textContent");
        Assertions.assertEquals(newStatus, status, "Статус задачи не изменился на " + status);
        return this;
    }
}
