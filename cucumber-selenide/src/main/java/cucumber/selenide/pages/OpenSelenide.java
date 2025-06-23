package cucumber.selenide.pages;

import com.codeborne.selenide.Selenide;

public class OpenSelenide {

    public static void open(String path) {
        Selenide.open(path);
    }
}
