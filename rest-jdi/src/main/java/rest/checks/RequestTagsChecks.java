package rest.checks;

import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;
import rest.dto.errorDto.ErrorResponseDto;
import rest.dto.tagsDto.ResponseTagsDto;
import rest.dto.tagsDto.TagsDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static rest.common.Constants.*;

public class RequestTagsChecks {

    public void checkTagsInTask(TagsDto addTags, List<ResponseTagsDto> getTags) {
        try {
            for (ResponseTagsDto getTag : getTags) {
                if (addTags.getId().equals("getTag.getId()")) {
                    Assertions.assertEquals(getTag.getName(), addTags.getName(), "Tag имеет не верные данные");
                    break;
                }
            }
        } catch (AssertionFailedError e) {
            throw new AssertionFailedError("Tag с id " + addTags.getId() + " не был добавлен" + e);
        }
    }

    public void checkAddTag(TagsDto addTagsDto, List<TagsDto> getListTags) {
        try {
            for (TagsDto getTag : getListTags) {
                if (addTagsDto.getId().equals(getTag.getId())) {
                    Assertions.assertEquals(getTag.getName(), NAME_TAG, "Tag имеет не верные данные");
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

    public void createAnExistingTag(ErrorResponseDto error) {
        assertAll("Check error tag response",
                () -> Assertions.assertEquals(error.getError_children().get(0).getError(), ERROR, "Ошибка в ответе не соответствует " + ERROR),
                () -> Assertions.assertEquals(error.getError_children().get(0).getError_description(), ERROR_DESCRIPTION, "Ошибка в ответе не соответствует " + ERROR_DESCRIPTION),
                () -> Assertions.assertEquals(error.getError_children().get(0).getError_developer_message(), ERROR_DEVELOPER_MESSAGE, "Ошибка в ответе не соответствует " + ERROR_DEVELOPER_MESSAGE)
        );
    }

    public void deleteNotCreateTag(ErrorResponseDto error) {
        assertAll("Check error tag response",
                () -> Assertions.assertEquals(error.getError(), "Not Found", "Ошибка в ответе не соответствует"),
                () -> Assertions.assertEquals(error.getError_description(), "Entity with id 10-50992 not found", "Ошибка в ответе не соответствует")
        );
    }
}
