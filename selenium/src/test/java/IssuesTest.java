import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import selenium.api.annotations.CreateTask;
import selenium.api.annotations.DeleteTask;
import selenium.driver.DriverManager;
import selenium.pages.IssuesPage;

import static selenium.utils.Path.ISSUES;

@ExtendWith(DriverManager.class)
public class IssuesTest {

//    Тест кейс 1. Создание новой задачи.
//    Предусловие: открыта страница issues
//    Шаги:
//    1. Кликнуть по кнопке "Новая задача"
//    2. Ввести заголовок
//	  3. Нажать на кнопку "Создать"
//    Ожидаемый результат: Только что созданная задача создана и сразу открыта.
//    Задача присутствует в общем списке в таблице задач.

    @Test
    @DeleteTask
    @DisplayName("Создание новой задачи")
    void createNewTask() {
        new IssuesPage()
                .open(ISSUES.getPath())
                .clickButtonCreateTask()
                .inputTitleTask()
                .clickButtonCreate()
                .checkTheNameTaskInDialog()
                .checkCreateTask();
    }

//  Тест кейс 2. Редактирование названия задачи
//  Предусловие: задача создана, открыта страница issues
//  Шаги:
//	1. Открыть задачу
//	2. Нажать на иконку редактирования. Поле становится активным для ввода
//	3. Ввести новое название задачи
//	4. Нажать на кнопку "Сохранить"
//    Ожидаемый результат: Название задачи изменилось на новое на странице задачи и в списке всех задач на странице issues.

    @Test
    @CreateTask
    @DeleteTask
    @DisplayName("Редактирование названия задачи")
    void editTitleTask() {
        new IssuesPage()
                .open(ISSUES.getPath())
                .clickLinkTicketId()
                .clickButtonEditTask()
                .editTitleTask()
                .goPageIssues()
                .checkNewTitleTask();
    }

//    Тест кейс 3. Поиск задачи.
//    Предусловие: открыта страница issues.
//    Шаги:
//      1. Кликнуть по полю ввода в поисковой строке
//	    2. Ввести название задачи
//	    3. Нажать на кнопку поиска (кнопка с лупой)
//      Ожидаемый результат: В таблице отображается только та задача, которая ищется.

    @Test
    @CreateTask
    @DeleteTask
    @DisplayName("Поиск задачи")
    void taskSearch() {
        new IssuesPage()
                .open(ISSUES.getPath())
                .inputQueryInSearch();
    }

//    Тест кейс 4. Изменение статуса задачи.
//    Предусловие: Открыта страница задачи.
//
//     Шаги:
//     1. Нажать на поле "Состояние"
//     2. В выпадающем списке выбрать "Открыта"
//     Ожидаемый результат: Состояние задачи изменилось на
//     "Открыта" на странице задачи. На странице issues в таблице списка задач статус задачи изменился на "Открыта".

    @Test
    @CreateTask
    @DeleteTask
    @DisplayName("Изменение статуса задачи")
    void changTaskStatus() {
        new IssuesPage()
                .open(ISSUES.getPath())
                .clickLinkTicketId()
                .clickFieldLabelStatus()
                .selectStatusOpen()
                .goPageIssues()
                .checkStatusChanged();
    }

//    Тест кейс 5. Удаление задачи.
//    Предусловие: Открыта страница issues
//    Шаги:
//    1. Кликнуть на задачу
//	  2. Нажать на иконку три точки "Показать больше"
//    3. В выпадающем списке выбрать "Удалить задачу"
//    4. В появившемся окне подтверждения нажать "Удалить"
//    5. Обновить страницу
//    Ожидаемый результат: задача удалена из списка в таблице с задачами.

    @Test
    @CreateTask
    @DisplayName("Удаление задачи")
    void deleteTask() {
        new IssuesPage()
                .open(ISSUES.getPath())
                .clickLinkTicketId()
                .clickButtonShowMore()
                .selectDeleteTask()
                .checkDeleteTask();
    }
}
