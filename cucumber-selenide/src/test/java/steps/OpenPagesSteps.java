package steps;

import io.cucumber.java.ru.Дано;

import static cucumber.selenide.pages.OpenSelenide.open;

public class OpenPagesSteps {

    @Дано("Открываем сайт {string}")
    public void openIssues(String arg) {
        open(arg);
    }
}
