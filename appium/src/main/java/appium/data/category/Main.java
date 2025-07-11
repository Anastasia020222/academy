package appium.data.category;

import lombok.Getter;

@Getter
public enum Main {

    ACCESS_IBILITY("Access'ibility"),
    ACCESSIBILITY("Accessibility"),
    ANIMATION("Animation"),
    APP("App"),
    CONTENT("Content"),
    GRAPHICS("Graphics"),
    MEDIA("Media"),
    NFC("NFC"),
    OS("OS"),
    PREFERENCE("Preference"),
    TEXT("Text"),
    VIEWS("Views");

    private final String category;

    Main(String category) {
        this.category = category;
    }
}
