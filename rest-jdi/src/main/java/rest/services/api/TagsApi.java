package rest.services.api;

import com.epam.http.response.RestResponse;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import rest.dto.errorDto.ErrorResponseDto;
import rest.dto.tagsDto.ResponseTagsDto;
import rest.dto.tagsDto.TagsDto;

import java.util.List;

import static rest.common.Path.*;
import static rest.services.api.APIServiceTags.*;
import static utils.JsonFileToString.getRequestBody;

@NoArgsConstructor
public class TagsApi {

    public TagsDto createTags(String path) {
        RestResponse response = createTag.post(getRequestBody(path));
        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");
        return response.getRaResponse().then().log().all().extract().body().as(TagsDto.class);
    }

    public List<ResponseTagsDto> getListTagsInTask(String idTask) {
        RestResponse response = getListTagsTask.pathParams(idTask).call();
        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");
        return response.getRaResponse().then().log().all().extract().body().jsonPath().getList("", ResponseTagsDto.class);
    }

    public List<TagsDto> getListTags() {
        RestResponse response = getAllTags.call();
        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");
        return response.getRaResponse().then().log().all().extract().body().jsonPath().getList("", TagsDto.class);
    }

    public void deleteTag(String idTag) {
        RestResponse response = deleteTag.pathParams(idTag).call();
        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(200, code, "Статус код не равен 200");
    }

    public ErrorResponseDto createAsExistingTag() {
        RestResponse response = createTag.post(getRequestBody(REQUEST_TAGS));
        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(400, code, "Статус код не равен 400");
        return response.getRaResponse().then().log().all().extract().body().as(ErrorResponseDto.class);
    }

    public ErrorResponseDto deleteInvalidTag(String idTag) {
        RestResponse response = deleteTag.pathParams(idTag).call();
        int code = response.getRaResponse().statusCode();
        Assertions.assertEquals(404, code, "Статус код не равен 404");
        return response.getRaResponse().then().log().all().extract().body().as(ErrorResponseDto.class);
    }
}
