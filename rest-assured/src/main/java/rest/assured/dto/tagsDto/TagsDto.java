package rest.assured.dto.tagsDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagsDto {

    private String name;
    private String id;

    @JsonProperty("$type")
    private String type;
}
