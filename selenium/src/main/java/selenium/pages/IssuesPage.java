package selenium.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static selenium.api.ApiRequest.getTask;
import static selenium.utils.Constants.*;

public class IssuesPage extends AbsBasePage<IssuesPage> {

    @FindBy(css = "[data-test='createIssueButton']")
    private WebElement buttonNewTask;

    @FindBy(css = "[data-test='ring-island ring-dialog']")
    private WebElement dialog;

    @FindBy(css = "[data-test='summary']")
    private WebElement summary;

    @FindBy(css = "[data-test='submit-button']")
    private WebElement buttonCreate;

    @FindBy(css = "[data-test='ticket-summary']")
    private WebElement ticketSummary;

    @FindBy(css = "[data-test='ring-dialog-close-button']")
    private WebElement dialogCloseButton;

    @FindBy(css = "[data-test='ring-link ticket-id']")
    private WebElement linkTicketId;

    @FindBy(css = "[data-test='ring-table-body']")
    private WebElement table;

    @FindBy(xpath = "//a[@data-test='ring-link']")
    private WebElement idTask;

    @FindBy(xpath = "//div[@data-test='ring-input']//input[@data-test='ring-select__focus']")
    private WebElement fieldSearch;

    @FindBy(xpath = "//div[@data-test='toolbar-search']//button[@data-test='search-button']")
    private WebElement buttonSearch;

    @FindBy(css = "tbody[data-test='ring-table-body']")
    private WebElement tableRow;

    @FindBy(xpath = "//div[@data-test='error-message']")
    private WebElement errorMessageEmptyTask;

    public static String id;

    public static String rowInTable = "//tbody[@data-test='ring-table-body']/*[starts-with(@data-test, 'ring-table-row')]";

    public IssuesPage clickButtonCreateTask() {
        buttonNewTask.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='ring-island ring-dialog']")));
        Assertions.assertTrue(dialog.isDisplayed(), "Окно создания задачи не открылось");
        Assertions.assertFalse(checkEnableButton(), "Кнопка 'Создать' стала активной");
        return this;
    }

    public IssuesPage inputTitleTask() {
        summary.sendKeys(NAME_TICKET);
        Assertions.assertTrue(checkEnableButton(), "Кнопка 'Создать' не стала активной");
        return this;
    }

    public IssuesPage checkInactiveButtonCreate() {
        Assertions.assertFalse(checkEnableButton(), "Кнопка 'Создать' не стала активной");
        return this;
    }

    public IssuesPage clickButtonCreate() {
        buttonCreate.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='ring-island ring-dialog']")));
        Assertions.assertTrue(dialog.isDisplayed(), "Окно задачи не открылось");

        System.out.println(this.task);
        if (this.task == null) {
            this.task = getTask();
        }
        id = idTask.getAttribute("textContent");
        task.setId(id);
        System.out.println("task " + task.getId());
        return this;
    }

    public IssuesPage checkCreateTask() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='ring-table-body']")));
        Map<String, String> map = getListTask();
        boolean found = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().contains(id)) {
                Assertions.assertEquals(entry.getValue(), NAME_TICKET,
                        "Название с id " + id + " не соответствует " + NAME_TICKET);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("Задачи с id " + id + " не оказалось в списке");
        }
        return this;
    }

    public IssuesPage checkTheNameTaskInDialog() {
        String attr = ticketSummary.getAttribute("textContent");
        Assertions.assertEquals(attr, NAME_TICKET, "Название задачи не появилось");

        dialogCloseButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[data-test='ring-island ring-dialog']")));
        return this;
    }

    public IssuePage clickLinkTicketId() {
        WebElement linkTitle = driver.findElement(By.xpath(String.format("//a[@data-test='ring-link ticket-id'][text()='%s']", task.getId())));
        linkTitle.click();
        return new IssuePage();
    }

    public Map<String, String> getListTask() {
        return wait
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    Map<String, String> tasks = new HashMap<>();

                    WebElement tableBody = d.findElement(By.xpath("//tbody[@data-test='ring-table-body']"));

                    List<WebElement> rows = tableBody.findElements(By.xpath("//tr[@data-test and contains(@data-test, 'ring-table-row')]"));

                    for (WebElement row : rows) {
                        WebElement taskNumber = row.findElement(By.xpath("//td[@data-test='ring-table-cell id']//span/a[@data-test='ring-link ticket-id']"));
                        WebElement taskName = row.findElement(By.xpath("//td[@data-test='ring-table-cell summary']//a[@href]"));

                        String numberText = taskNumber.getAttribute("textContent").trim();
                        String nameText = taskName.getAttribute("textContent").trim();

                        tasks.put(numberText, nameText);
                    }
                    return tasks;
                });
    }

    public IssuesPage checkStatusChanged() {
        return wait
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    WebElement tableBody = driver.findElement(By.xpath("//tbody[@data-test='ring-table-body']"));

                    List<WebElement> rows = tableBody.findElements(By.xpath("//tr[@data-test and contains(@data-test, 'ring-table-row')]"));

                    String status = rows.stream().filter(t -> t.findElement(By.xpath("//td[@data-test='ring-table-cell id']//span/a[@data-test='ring-link ticket-id']"))
                                    .getAttribute("textContent").equals(task.getId()))
                            .findAny()
                            .orElseThrow(() -> new RuntimeException("Задача с id " + task.getId() + " не найдена"))
                            .findElement(By.xpath("//span[@data-test='ring-tooltip field-value']")).getAttribute("textContent");
                    Assertions.assertEquals(status, STATUS_OPEN, "Статус задачи не изменился на 'Open' на странице задач");
                    return new IssuesPage();
                });
    }

    private boolean checkEnableButton() {
        String disabledAttr = buttonCreate.getAttribute("disabled");
        return disabledAttr == null;
    }

    public IssuesPage checkNewTitleTask() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='ring-table-body']")));
        String taskId = task.getId();
        Map<String, String> taskMap = getListTask();
        boolean found = false;
        for (Map.Entry<String, String> entry : taskMap.entrySet()) {
            if (entry.getKey().contains(taskId)) {
                Assertions.assertEquals(entry.getValue(), NEW_NAME_TICKET,
                        "Название задачи с id " + taskId + " не соответствует ожидаемому: " + NEW_NAME_TICKET);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("Задачи с id " + taskId + " не оказалось в списке");
        }
        return this;
    }

    public IssuesPage checkDeleteTask() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='ring-table-body']")));

        Map<String, String> map = getListTask();
        for (Map.Entry<String, String> el : map.entrySet()) {
            Assertions.assertFalse(el.getKey().equals(task.getId()), "Задача с id " + task.getId() + " не была удалена");
        }
        return this;
    }

    public IssuesPage inputQueryInSearch() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='ring-table-body']")));
        fieldSearch.sendKeys(NAME_TICKET);
        buttonSearch.click();
        Map<String, String> map = getListTask();
        for (Map.Entry<String, String> m : map.entrySet()) {
            System.out.println("m " + m.getKey());
            Assertions.assertEquals(m.getValue(), NAME_TICKET, "Название найденной задачи не соответствует " + NAME_TICKET);
        }
        return this;
    }

    public IssuesPage incorrectInputQueryInSearch() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='ring-table-body']")));
        fieldSearch.sendKeys("Data");
        buttonSearch.click();
        Map<String, String> map = getListTask();
        map.entrySet().stream()
                .filter(f -> f.getValue().equals("Data"))
                .findAny()
                .ifPresent(empty -> {
                            throw new RuntimeException("Задача с именем Data оказалась в списке");
                        }
                );
        return this;
    }

    public IssuesPage checkErrorMessage() {
        String message = errorMessageEmptyTask.getAttribute("textContent");
        Assertions.assertEquals(message, ERROR_MESSAGE_EMPTY_TASK, "Текст сообщения об ошибке не верный");
        return this;
    }
}
