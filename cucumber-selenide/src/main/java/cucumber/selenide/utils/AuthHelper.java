package cucumber.selenide.utils;

import com.codeborne.selenide.LocalStorage;
import com.codeborne.selenide.Selenide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static cucumber.selenide.pages.OpenSelenide.open;

public class AuthHelper {

    public void loginWithLocalStorageToken() {
        open("");
        LocalStorage localStorage = Selenide.localStorage();
        localStorage.setItem("cf6f74d5-c1b8-457f-9d4b-2348fe19440f-token", readTokenJson());
        Selenide.refresh();
    }

    public static String readTokenJson() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/token.json")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read token.json", e);
        }
    }
}
