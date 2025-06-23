package steps;

import cucumber.selenide.pages.IssuesPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.*;

import java.util.Map;

public class IssuesSteps {

    private final IssuesPage issuesPage = new IssuesPage();

    @Когда("Переходим на страницу задачи")
    public void goPageIssue() {
        issuesPage
                .clickLinkTicketId();
    }

    @И("Выбираем опцию Редактирование названия задачи")
    public void selectOptionEditTask() {
        issuesPage
                .selectOptionEditTask();
    }

    @Если("Вводим новое название и описание задачи")
    public void inputNewTitleTask(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        issuesPage
                .inputNewTitleTask(data.get("Название"))
                .inputNewDescriptionTask(data.get("Описание"));
    }

    @И("Нажимаем на кнопку Сохранить")
    public void clickButtonSave() {
        issuesPage
                .clickButtonSave();
    }

    @Тогда("Задача сохраняется. Название и описание задачи изменилось на новое")
    public void checkSaveTitleTask() {
        issuesPage
                .checkSaveTitleTask();
    }

    @И("Нажимаем на поле {string}")
    public void clickFieldLabelStatus(String condition) {
        issuesPage
                .clickFieldLabelStatus(condition);
    }

    @Если("В выпадающем меню выбирать статус {string}")
    public void selectStatus(String status) {
        issuesPage
                .selectStatus(status);
    }

    @Тогда("Статус на странице задачи изменится на {string}")
    public void checkStatusTask(String status) {
        issuesPage
                .checkStatusTask(status);
    }
}
