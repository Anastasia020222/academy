package rest.checks;

import org.junit.jupiter.api.Assertions;
import rest.dto.errorDto.ErrorResponseDto;

import static org.junit.jupiter.api.Assertions.assertAll;

public class RequestNegativeTaskCheck {

    public void editInvalidStatusTask(ErrorResponseDto error) {
        assertAll("Check error tag response",
                () -> Assertions.assertEquals(error.getError_description(), "Недопустимое значение", "Ошибка в ответе не соответствует"),
                () -> Assertions.assertEquals(error.getError_developer_message(), "Недопустимое значение", "Ошибка в ответе не соответствует")
        );
    }

    public void editNameTaskEmpty(ErrorResponseDto error) {
        assertAll("Check error tag response",
                () -> Assertions.assertEquals(error.getError(), "invalid_properties", "Ошибка не соответствует"),
                () -> Assertions.assertEquals(error.getError_description(), "Property Issue.summary is invalid", "Ошибка в ответе не соответствует"),
                () -> Assertions.assertEquals(error.getError_children().get(0).getError(), "Issue.summary-is-invalid", "Ошибка в ответе не соответствует"),
                () -> Assertions.assertEquals(error.getError_children().get(0).getError_description(), "Property Issue.summary is invalid", "Ошибка не соответствует"),
                () -> Assertions.assertEquals(error.getError_children().get(0).getError_developer_message(), "Value for summary is required", "Ошибка в ответе не соответствует")
        );
    }
}
