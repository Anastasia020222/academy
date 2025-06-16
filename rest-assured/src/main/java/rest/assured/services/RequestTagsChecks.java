package rest.assured.services;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;
import rest.assured.dto.errorDto.ErrorResponseDto;
import rest.assured.dto.tagsDto.TagsDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static rest.assured.utils.Constants.*;
import static rest.assured.utils.Constants.ERROR_DEVELOPER_MESSAGE;

public class RequestTagsChecks {

    public void checkAddTag(TagsDto addTagsDto, List<TagsDto> getListTags) {
        try {
            for (TagsDto getTag : getListTags) {
                if (addTagsDto.getId().equals(getTag.getId())) {
                    Assertions.assertEquals(getTag.getName(), addTagsDto.getName(), "Tag имеет не верные данные");
                    break;
                }
            }
        } catch (AssertionFailedError e) {
            throw new AssertionFailedError("Tag с id " + addTagsDto.getId() + " не был добавлен" + e);
        }
    }

    public void checkDeleteTag(String idTag, List<TagsDto> getListTags) {
        for (TagsDto getTag : getListTags) {
            if (idTag.equals(getTag.getId())) {
                throw new AssertionFailedError("Tag с id " + idTag + " не был удален");
            }
        }
    }

    public void createAnExistingTag(Response responseCreateAnExistingTag) {
        int code = responseCreateAnExistingTag.statusCode();
        Assertions.assertEquals(400, code, "Статус код не равен 400");

        ErrorResponseDto error = responseCreateAnExistingTag.body().as(ErrorResponseDto.class);

        assertAll("Check error tag response",
                () -> Assertions.assertEquals(error.getError_children().get(0).getError(), ERROR, "Ошибка в ответе не соответствует " + ERROR),
                () -> Assertions.assertEquals(error.getError_children().get(0).getError_description(), ERROR_DESCRIPTION, "Ошибка в ответе не соответствует " + ERROR_DESCRIPTION),
                () -> Assertions.assertEquals(error.getError_children().get(0).getError_developer_message(), ERROR_DEVELOPER_MESSAGE, "Ошибка в ответе не соответствует " + ERROR_DEVELOPER_MESSAGE)
        );
    }

    public void deleteNotCreateTag(Response response) {
        int code = response.statusCode();
        Assertions.assertEquals(404, code, "Статус код не равен 404");

        ErrorResponseDto error = response.body().as(ErrorResponseDto.class);

        assertAll("Check error tag response",
                () -> Assertions.assertEquals(error.getError(), "Not Found", "Ошибка в ответе не соответствует"),
                () -> Assertions.assertEquals(error.getError_description(), "Entity with id 10-50992 not found", "Ошибка в ответе не соответствует")
        );
    }
}
