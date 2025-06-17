import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import selenium.api.annotations.CreateTask;
import selenium.api.annotations.DeleteTask;
import selenium.driver.DriverManager;
import selenium.pages.IssuesPage;

import static selenium.utils.Path.ISSUES;

@ExtendWith(DriverManager.class)
public class NegativeIssuesTask {

    //Тест кейс 6. Редактирование задачи с пустым полем в названии
    //Предусловие: задача создана
    //Шаги:
    //1. Открыть задачу
    //2. Нажать на кнопку редактирования
    //3. Удалить содержимое в поле. Оставить поле пустым
    //4. Нажать на кнопку "Сохранить"
    //О.Р. Изменения не сохранились. Появляется алерт с ошибкой.
    @Test
    @CreateTask
    @DeleteTask
    @DisplayName("Редактирование названия задачи. Пустое поле")
    void editEmptyTitleTask() {
        new IssuesPage()
                .open(ISSUES.getPath())
                .clickLinkTicketId()
                .clickButtonEditTask()
                .editTitleEmptyTask();
    }

    //Тест кейс 7. Поиск задачи, которой нет в списке
    //Предусловие: открыта страница issues
    //Шаги:
    //1. Кликнуть по полю ввода в поисковой строке
    //2. Ввести в поле название не созданной задачи
    //3. Нажать на икону лупы для поиска
    //4. О.Р. В таблице задача, указанная при поиске, отсутствует

    @Test
    @DisplayName("Поиск задачи, которой нет в списке")
    void taskSearchInNotList() {
        new IssuesPage()
                .open(ISSUES.getPath())
                .incorrectInputQueryInSearch()
                .checkErrorMessage();
    }

    //Тест-кейс 8. Создание задачи с пустым заголовком
    //Предусловие: открыта страница issues
    //Шаги:
    //1. Нажать на кнопку "Создать"
    //2. Поле названия задачи оставить пустым
    //О.Р. Кнопка "Создать" не активна для клика
    @Test
    @DisplayName("Создание задачи с пустым полем названия")
    void createTaskEmptyTitle() {
        new IssuesPage()
                .open(ISSUES.getPath())
                .clickButtonCreateTask()
                .checkInactiveButtonCreate();
    }
}
