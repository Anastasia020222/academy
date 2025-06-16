package rest.assured.dto.tagsDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTagsDto {
    private String name;
    private String id;

    @JsonProperty("$type")
    private String type;
}
