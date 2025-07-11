package appium.data.category;

import lombok.Getter;

@Getter
public enum Notification {

    INCOMING_MESSAGE("IncomingMessage"),
    NOTIFYING_SERVICE("Notifying Service Controller"),
    NOTIFY_WITH_TEXT("NotifyWithText"),
    STATUS_BAR("Status Bar");

    private String category;

    Notification(String category) {
        this.category = category;
    }
}
