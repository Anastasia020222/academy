import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rest.assured.api.TagsApi;
import rest.assured.dto.tagsDto.TagsDto;
import rest.assured.services.RequestTagsChecks;

import static rest.assured.utils.Constants.INVALID_TAG;
import static rest.assured.utils.Path.REQUEST_TAGS;

public class NegativeTagTest {

    private final TagsApi tagsApi = new TagsApi();
    private final RequestTagsChecks requestTagsChecks = new RequestTagsChecks();

    @Test
    @DisplayName("Создание уже существующего тега")
    void createAnExistingTag() {
        Response responseCreateNewTag = tagsApi.createTag(REQUEST_TAGS);
        Response responseCreateAnExistingTag = tagsApi.createTag(REQUEST_TAGS);
        TagsDto tagsDto = responseCreateNewTag.body().as(TagsDto.class);

        requestTagsChecks
                .createAnExistingTag(responseCreateAnExistingTag);
        tagsApi
                .deleteTag(tagsDto.getId());
    }

    @Test
    @DisplayName("Удаление не созданного тега")
    void deleteNotCreateTag() {
        Response response = tagsApi.deleteTag(INVALID_TAG);
        requestTagsChecks
                .deleteNotCreateTag(response);
    }
}
