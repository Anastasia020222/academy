package appium.data.category;

import lombok.Getter;

@Getter
public enum Views {

    ANIMATION("Animation"),
    AUTO_COMPLETE("Auto Complete"),
    BUTTONS("Buttons"),
    CHRONOMETER("Chronometer"),
    CONTROLS("Controls"),
    CUSTOM("Custom"),
    DATE_Widgets("Date Widgets"),
    DRAG_AND_DROP("Drag and Drop"),
    EXPANDABLE_LISTS("Expandable Lists"),
    FOCUS("Focus"),
    GALLERY("Gallery"),
    GAME_CONTROLLER_INPUT("Game Controller Input"),
    GRID("Grid"),
    HOVER_EVENTS("Hover Events"),
    IMAGE_BUTTON("ImageButton"),
    IMAGES_WITCHER("ImageSwitcher"),
    IMAGE_VIEW("ImageView"),
    LAYOUT_ANIMATION("Layout Animation"),
    LAYOUTS("Layouts"),
    LISTS("Lists"),
    PICKER("Picker"),
    POPUP_MENU("Popup Menu"),
    PROGRESS_BAR("Progress Bar"),
    RADIO_GROUP("Radio Group"),
    RATING_BAR("Rating Bar"),
    ROTATING_BUTTON("Rotating Button"),
    SCROLL_BAR("Scroll Bar"),
    WEB_VIEW("WebView");

    private String category;

    Views(String category) {
        this.category = category;
    }
}
