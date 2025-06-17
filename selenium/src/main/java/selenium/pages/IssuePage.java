package selenium.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.component.DialogDeleteTask;
import selenium.component.DropdownShowMore;
import selenium.component.ListIssueStatuses;

import static selenium.utils.Constants.*;

public class IssuePage extends AbsBasePage<IssuePage> {

    ListIssueStatuses listIssueStatuses;
    DropdownShowMore dropdownShowMore;
    DialogDeleteTask dialogDeleteTask;

    public IssuePage() {
        this.listIssueStatuses = new ListIssueStatuses();
        this.dropdownShowMore = new DropdownShowMore();
        this.dialogDeleteTask = new DialogDeleteTask();
    }

    @FindBy(css = "[data-test='issue-container']")
    private WebElement pageIssue;

    @FindBy(xpath = "//div[@class='summaryToolbar__c231']//span[@data-test-title='Показать больше']//button")
    private WebElement buttonShowMore;

    @FindBy(xpath = "//div[@class='summaryToolbar__c231']//span[@data-test-title='Редактировать задачу']//button")
    private WebElement buttonEditTask;

    @FindBy(xpath = "//div[@class='summaryRow__e912']//textarea[@data-test='summary']")
    private WebElement editTitleTask;

    @FindBy(css = "[data-test='save-button']")
    private WebElement buttonSaveTask;

    @FindBy(css = "[data-test='ticket-summary']")
    private WebElement summaryTask;

    @FindBy(xpath = "//div[@data-test='breadcrumbs-container']//a[@data-test='ring-link breadcrumb-issues-link']")
    private WebElement linkPageIssues;

    @FindBy(xpath = "//div[@data-test='field']//label[@data-test='field-label'][text()='Состояние']")
    private WebElement fieldLabelStatus;

    @FindBy(xpath = "//span[@data-test='ring-list-item-label' and text()='Открыта']")
    private WebElement statusOpen;

    @FindBy(xpath = "//div[@data-test='field' and @aria-label='Состояние']//span[@data-test='ring-tooltip field-value']")
    private WebElement selectStatus;

    @FindBy(xpath = "//button[.//span[contains(text(), 'Удалить задачу')]]")
    private WebElement buttonDelete;

    @FindBy(css = "[data-test='confirm-ok-button']")
    private WebElement confirmOkButton;

    @FindBy(xpath = "//div[@data-test='alert' and @data-test-type='error']")
    private WebElement alertErrorTitleTaskEmpty;

    public IssuePage clickButtonEditTask() {
        buttonEditTask.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ticketEditForm__f5b8")));
        return this;
    }

    public IssuePage editTitleTask() {
        editTitleTask.click();
        editTitleTask.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        editTitleTask.sendKeys(Keys.BACK_SPACE);
        editTitleTask.sendKeys(NEW_NAME_TICKET);
        buttonSaveTask.click();

        String summary = summaryTask.getAttribute("textContent");
        Assertions.assertEquals(summary, NEW_NAME_TICKET, "Название задачи не изменилось");
        return this;
    }

    public IssuePage editTitleEmptyTask() {
        editTitleTask.click();
        editTitleTask.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        editTitleTask.sendKeys(Keys.BACK_SPACE);
        buttonSaveTask.click();

        Assertions.assertTrue(alertErrorTitleTaskEmpty.isDisplayed(), "Алерт с ошибкой не отобразился");

        String nameError = alertErrorTitleTaskEmpty.getAttribute("textContent");

        System.out.println(nameError);
        Assertions.assertEquals(nameError, ERROR_TITLE_EMPTY_TASK, "Текст ошибки не верный");
        return this;
    }

    public IssuePage clickFieldLabelStatus() {
        fieldLabelStatus.click();
        listIssueStatuses.openListStatus();
        return this;
    }

    public IssuePage selectStatusOpen() {
        statusOpen.click();
        listIssueStatuses.closeListStatus();
        String status = selectStatus.getAttribute("textContent");
        Assertions.assertEquals(status, STATUS_OPEN, "Статус задачи не изменился на " + STATUS_OPEN);
        return this;
    }

    public IssuePage clickButtonShowMore() {
        buttonShowMore.click();
        dropdownShowMore.openDropdownShowMore();
        return this;
    }

    public IssuesPage selectDeleteTask() {
        buttonDelete.click();
        dropdownShowMore.closeDropdownShowMore();
        dialogDeleteTask.openDialogDeleteTask();
        confirmOkButton.click();
        dialogDeleteTask.closeDialogDeleteTask();
        driver.navigate().refresh();
        return new IssuesPage();
    }

    public IssuesPage goPageIssues() {
        linkPageIssues.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("tr[data-test^='ring-table-row']")
        ));
        return new IssuesPage();
    }
}
