package appium.data.category;

import lombok.Getter;

@Getter
public enum App {

    ACTION_BAR("Action Bar"),
    ACTIVITY("Activity"),
    ALARM("Alarm"),
    ALERT_DIALOGS("Alert Dialogs"),
    DEVICE_ADMIN("Device Admin"),
    FRAGMENT("Fragment"),
    LAUNCHER_SHORTCUTS("Launcher Shortcuts"),
    LOADER("Loader"),
    MENU("Menu"),
    NOTIFICATION("Notification"),
    SEARCH("Search"),
    SERVICE("Service"),
    TEXT_TO_SEECH("Text-To-Speech"),
    VOICE_RECOGNITION("Voice Recognition");

    private final String app;

    App(String app) {
        this.app = app;
    }
}
