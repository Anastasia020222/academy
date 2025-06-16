import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import rest.ApiManager;
import rest.checks.RequestTagsChecks;
import rest.dto.errorDto.ErrorResponseDto;
import rest.dto.tagsDto.TagsDto;
import rest.services.api.TagsApi;

import static rest.common.Constants.INVALID_TAG;
import static rest.common.Path.REQUEST_TAGS;

@ExtendWith(ApiManager.class)
public class NegativeTagTest {

    private final TagsApi tagsApi = new TagsApi();
    private final RequestTagsChecks requestTagsChecks = new RequestTagsChecks();

    @Test
    @DisplayName("Создание уже существующего тега")
    void createAnExistingTag() {
        TagsDto createTag = tagsApi.createTags(REQUEST_TAGS);
        ErrorResponseDto reCreateTag = tagsApi.createAsExistingTag();

        requestTagsChecks
                .createAnExistingTag(reCreateTag);
        tagsApi
                .deleteTag(createTag.getId());
    }

    @Test
    @DisplayName("Удаление не созданного тега")
    void deleteNotCreateTag() {
        ErrorResponseDto error = tagsApi.deleteInvalidTag(INVALID_TAG);
        requestTagsChecks
                .deleteNotCreateTag(error);
    }
}
