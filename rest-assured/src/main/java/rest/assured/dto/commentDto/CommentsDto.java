package rest.assured.dto.commentDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDto {

    private String text;
    private String id;

    @JsonProperty("$type")
    private String type;
}
