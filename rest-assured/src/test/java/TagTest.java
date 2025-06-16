import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rest.assured.dto.tagsDto.TagsDto;
import rest.assured.services.RequestTagsChecks;
import rest.assured.api.TagsApi;

import java.util.List;

import static rest.assured.utils.Path.REQUEST_DELETE_TAG;
import static rest.assured.utils.Path.REQUEST_TAGS;

public class TagTest {

    private final TagsApi tagsApi = new TagsApi();
    private final RequestTagsChecks requestTagsChecks = new RequestTagsChecks();

    @Test
    @DisplayName("Создать новый тег")
    void createNewTag() {
        Response response = tagsApi.createTag(REQUEST_TAGS);
        TagsDto tagsDto = response.body().as(TagsDto.class);
        List<TagsDto> list = tagsApi.getAllTags().body().jsonPath().getList("", TagsDto.class);

        requestTagsChecks
                .checkAddTag(tagsDto, list);
        tagsApi
                .deleteTag(tagsDto.getId());
    }

    @Test
    @DisplayName("Удалить существующий тег")
    void deleteTag() {
        Response response = tagsApi.createTag(REQUEST_DELETE_TAG);
        TagsDto tagsDto = response.body().as(TagsDto.class);
        tagsApi
                .deleteTag(tagsDto.getId());

        List<TagsDto> list = tagsApi.getAllTags().body().jsonPath().getList("", TagsDto.class);

        requestTagsChecks
                .checkDeleteTag(tagsDto.getId(), list);
    }
}
