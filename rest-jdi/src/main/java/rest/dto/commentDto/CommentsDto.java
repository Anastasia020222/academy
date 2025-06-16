package rest.dto.commentDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDto {

    private String text;
    private String id;

    @JsonProperty("$type")
    private String type;
}
