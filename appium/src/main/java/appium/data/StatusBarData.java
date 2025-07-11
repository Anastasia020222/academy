package appium.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class StatusBarData {

    public static Stream<Arguments> statusBarNoty() {
        return Stream.of(
                Arguments.of("happy", "Mood ring", "I am happy"),
                Arguments.of("neutral", "Mood ring", "I am ok"),
                Arguments.of("sad", "Mood ring", "I am sad")
        );
    }

    public static Stream<Arguments> listDialogs() {
        return Stream.of(
                Arguments.of("Command one", "You selected: 0 , Command one"),
                Arguments.of("Command two", "You selected: 1 , Command two"),
                Arguments.of("Command three", "You selected: 2 , Command three"),
                Arguments.of("Command four", "You selected: 3 , Command four")
        );
    }
}
