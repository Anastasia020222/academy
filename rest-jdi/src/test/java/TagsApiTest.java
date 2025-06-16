import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import rest.ApiManager;
import rest.checks.RequestTagsChecks;
import rest.dto.tagsDto.TagsDto;
import rest.services.api.TagsApi;

import java.util.List;

import static rest.common.Path.REQUEST_DELETE_TAG;
import static rest.common.Path.REQUEST_TAGS;

@ExtendWith(ApiManager.class)
public class TagsApiTest {

    private final TagsApi tagsApi = new TagsApi();
    private final RequestTagsChecks requestTagsChecks = new RequestTagsChecks();

    @Test
    @DisplayName("Создать новый тег")
    void createNewTag() {
        TagsDto createTag = tagsApi.createTags(REQUEST_TAGS);
        List<TagsDto> list = tagsApi.getListTags();

        requestTagsChecks.checkAddTag(createTag, list);
        tagsApi.deleteTag(createTag.getId());
    }

    @Test
    @DisplayName("Удалить существующий тег")
    void deleteTag() {
        TagsDto createTag = tagsApi.createTags(REQUEST_DELETE_TAG);
        tagsApi.deleteTag(createTag.getId());
        List<TagsDto> list = tagsApi.getListTags();
        requestTagsChecks
                .checkDeleteTag(createTag.getId(), list);
    }
}
