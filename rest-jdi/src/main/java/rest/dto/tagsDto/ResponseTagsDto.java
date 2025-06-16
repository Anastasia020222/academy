package rest.dto.tagsDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTagsDto {
    private String name;
    private String id;

    @JsonProperty("$type")
    private String type;
}
